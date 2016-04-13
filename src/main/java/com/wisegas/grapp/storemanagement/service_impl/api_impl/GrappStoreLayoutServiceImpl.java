package com.wisegas.grapp.storemanagement.service_impl.api_impl;

import com.wisegas.common.domain.model.DomainEventPublisher;
import com.wisegas.common.domain.model.DomainEventSubscriber;
import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.value.CodeName;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreFeature;
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNode;
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNodeItem;
import com.wisegas.grapp.storemanagement.domain.event.GrappStoreNodeModifiedEvent;
import com.wisegas.grapp.storemanagement.domain.repository.GrappStoreLayoutRepository;
import com.wisegas.grapp.storemanagement.domain.value.*;
import com.wisegas.grapp.storemanagement.service.api.GrappStoreLayoutService;
import com.wisegas.grapp.storemanagement.service.dto.*;
import com.wisegas.grapp.storemanagement.service_impl.factory.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Named
@Singleton
@Transactional
@ApplicationService
public class GrappStoreLayoutServiceImpl implements GrappStoreLayoutService {

   private final GrappStoreLayoutRepository grappStoreLayoutRepository;

   @Inject
   public GrappStoreLayoutServiceImpl(GrappStoreLayoutRepository grappStoreLayoutRepository) {
      this.grappStoreLayoutRepository = grappStoreLayoutRepository;
   }

   @Override
   public GrappStoreLayoutDto get(String id) {
      return GrappStoreLayoutDtoFactory.createDto(grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id)));
   }

   @Override
   public GrappStoreLayoutDto updateOuterOutline(String id, GeoPolygon outerPolygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      layout.setOuterOutline(outerPolygon);
      return GrappStoreLayoutDtoFactory.createDto(layout);
   }

   @Override
   public GrappStoreLayoutDto updateInnerOutline(String id, GeoPolygon innerPolygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      layout.setInnerOutline(innerPolygon);
      return GrappStoreLayoutDtoFactory.createDto(layout);
   }

   @Override
   public GrappStoreFeatureDto addFeature(String id, GeoPolygon polygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      GrappStoreFeature feature = layout.addFeature(polygon);
      return GrappStoreFeatureDtoFactory.createDto(feature);
   }

   @Override
   public GrappStoreFeatureDto reshapeFeature(String id, String featureID, GeoPolygon polygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      GrappStoreFeature feature = layout.reshapeFeature(GrappStoreFeatureId.fromString(featureID), polygon);
      return GrappStoreFeatureDtoFactory.createDto(feature);
   }

   @Override
   public void removeFeature(String id, String featureID) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      layout.removeFeature(GrappStoreFeatureId.fromString(featureID));
   }

   @Override
   public GrappStoreLayoutUpdateResultDTO<GrappStoreNodeDto> addNode(String id, String type, GeoPoint location) {
      GrappStoreNodeModificationEventSubscriber nodeModificationEventSubscriber = new GrappStoreNodeModificationEventSubscriber();
      DomainEventPublisher.instance().subscribe(nodeModificationEventSubscriber);
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      GrappStoreNode node = layout.addNode(GrappStoreNodeType.fromName(type), location);
      return GrappStoreLayoutUpdateResultDtoFactory.createDto(layout, GrappStoreNodeDtoFactory.createDto(node), nodeModificationEventSubscriber.getNodeIDs());
   }

   @Override
   public GrappStoreNodeDto moveNode(String id, String nodeID, GeoPoint location) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      GrappStoreNode grappStoreNode = layout.moveNode(GrappStoreNodeId.fromString(nodeID), location);
      return GrappStoreNodeDtoFactory.createDto(grappStoreNode);
   }

   @Override
   public void removeNode(String id, String nodeID) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      layout.removeNode(GrappStoreNodeId.fromString(nodeID));
   }

   @Override
   public GrappStoreLayoutUpdateResultDTO<GrappStoreNodeItemDto> addNodeItem(String id, String nodeID, CodeName item) {
      GrappStoreNodeModificationEventSubscriber nodeModificationEventSubscriber = new GrappStoreNodeModificationEventSubscriber();
      DomainEventPublisher.instance().subscribe(nodeModificationEventSubscriber);
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      GrappStoreNodeItem grappStoreNodeItem = layout.addNodeItem(GrappStoreNodeId.fromString(nodeID), new Item(item));
      return GrappStoreLayoutUpdateResultDtoFactory.createDto(layout, GrappStoreNodeItemDtoFactory.createDto(grappStoreNodeItem), nodeModificationEventSubscriber.getNodeIDs());
   }

   private static class GrappStoreNodeModificationEventSubscriber implements DomainEventSubscriber<GrappStoreNodeModifiedEvent> {

      private final List<String> nodeIDs = new ArrayList<>();

      @Override
      public Class<GrappStoreNodeModifiedEvent> getSubscribedEventType() {
         return GrappStoreNodeModifiedEvent.class;
      }

      @Override
      public void handleEvent(GrappStoreNodeModifiedEvent domainEvent) {
         nodeIDs.add(domainEvent.getNodeID());
      }

      public List<String> getNodeIDs() {
         return nodeIDs;
      }
   }
}
