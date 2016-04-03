package com.wisegas.grapp.service_impl.api_impl;

import com.wisegas.common.domain.model.DomainEventPublisher;
import com.wisegas.common.domain.model.DomainEventSubscriber;
import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.grapp.domain.entity.GrappStoreFeature;
import com.wisegas.grapp.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.domain.entity.GrappStoreNode;
import com.wisegas.grapp.domain.event.GrappStoreNodeModifiedEvent;
import com.wisegas.grapp.domain.repository.GrappStoreLayoutRepository;
import com.wisegas.grapp.domain.value.GrappStoreFeatureId;
import com.wisegas.grapp.domain.value.GrappStoreLayoutId;
import com.wisegas.grapp.domain.value.GrappStoreNodeId;
import com.wisegas.grapp.domain.value.GrappStoreNodeType;
import com.wisegas.grapp.service.api.GrappStoreLayoutService;
import com.wisegas.grapp.service.dto.GrappStoreFeatureDTO;
import com.wisegas.grapp.service.dto.GrappStoreLayoutDTO;
import com.wisegas.grapp.service.dto.GrappStoreLayoutUpdateResultDTO;
import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;
import com.wisegas.grapp.service_impl.factory.GrappStoreFeatureDTOFactory;
import com.wisegas.grapp.service_impl.factory.GrappStoreLayoutDTOFactory;
import com.wisegas.grapp.service_impl.factory.GrappStoreLayoutUpdateResultDTOFactory;
import com.wisegas.grapp.service_impl.factory.GrappStoreNodeDTOFactory;

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
   public GrappStoreLayoutDTO get(String id) {
      return GrappStoreLayoutDTOFactory.createDTO(grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id)));
   }

   @Override
   public GrappStoreLayoutDTO updateOuterOutline(String id, GeoPolygon outerPolygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      layout.setOuterOutline(outerPolygon);
      return GrappStoreLayoutDTOFactory.createDTO(layout);
   }

   @Override
   public GrappStoreLayoutDTO updateInnerOutline(String id, GeoPolygon innerPolygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      layout.setInnerOutline(innerPolygon);
      return GrappStoreLayoutDTOFactory.createDTO(layout);
   }

   @Override
   public GrappStoreFeatureDTO addFeature(String id, GeoPolygon polygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      GrappStoreFeature feature = layout.addFeature(polygon);
      return GrappStoreFeatureDTOFactory.createDTO(feature);
   }

   @Override
   public GrappStoreFeatureDTO reshapeFeature(String id, String featureID, GeoPolygon polygon) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      GrappStoreFeature feature = layout.reshapeFeature(GrappStoreFeatureId.fromString(featureID), polygon);
      return GrappStoreFeatureDTOFactory.createDTO(feature);
   }

   @Override
   public void removeFeature(String id, String featureID) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      layout.removeFeature(GrappStoreFeatureId.fromString(featureID));
   }

   @Override
   public GrappStoreLayoutUpdateResultDTO<GrappStoreNodeDTO> addNode(String id, String type, GeoPoint location) {
      GrappStoreNodeModificationEventSubscriber nodeModificationEventSubscriber = new GrappStoreNodeModificationEventSubscriber();
      DomainEventPublisher.instance().subscribe(nodeModificationEventSubscriber);
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      GrappStoreNode node = layout.addNode(GrappStoreNodeType.fromName(type), location);
      return GrappStoreLayoutUpdateResultDTOFactory.createDTO(layout, GrappStoreNodeDTOFactory.createDTO(node), nodeModificationEventSubscriber.getNodeIDs());
   }

   @Override
   public GrappStoreNodeDTO moveNode(String id, String nodeID, GeoPoint location) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      GrappStoreNode grappStoreNode = layout.moveNode(GrappStoreNodeId.fromString(nodeID), location);
      return GrappStoreNodeDTOFactory.createDTO(grappStoreNode);
   }

   @Override
   public void removeNode(String id, String nodeID) {
      GrappStoreLayout layout = grappStoreLayoutRepository.get(GrappStoreLayoutId.fromString(id));
      layout.removeNode(GrappStoreNodeId.fromString(nodeID));
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
