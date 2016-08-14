package com.wisegas.stores.service_impl.api_impl;

import com.wisegas.common.domain.event.DomainEventPublisher;
import com.wisegas.common.domain.event.DomainEventSubscriber;
import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.common.lang.spacial.GeoPolygon;
import com.wisegas.common.lang.value.CodeName;
import com.wisegas.stores.domain.entity.Feature;
import com.wisegas.stores.domain.entity.Node;
import com.wisegas.stores.domain.entity.NodeItem;
import com.wisegas.stores.domain.entity.StoreLayout;
import com.wisegas.stores.domain.event.NodeModifiedEvent;
import com.wisegas.stores.domain.repository.StoreLayoutRepository;
import com.wisegas.stores.domain.value.*;
import com.wisegas.stores.service.api.StoreLayoutService;
import com.wisegas.stores.service.dto.*;
import com.wisegas.stores.service_impl.factory.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Named
@Singleton
@Transactional
@ApplicationService
public class StoreLayoutServiceImpl implements StoreLayoutService {

   private final StoreLayoutRepository storeLayoutRepository;

   @Inject
   public StoreLayoutServiceImpl(StoreLayoutRepository storeLayoutRepository) {
      this.storeLayoutRepository = storeLayoutRepository;
   }

   @Override
   public StoreLayoutDto get(String id) {
      return StoreLayoutDtoFactory.createDto(storeLayoutRepository.get(StoreLayoutId.fromString(id)));
   }

   @Override
   public StoreLayoutDto updateOuterOutline(String id, GeoPolygon polygon) {
      StoreLayout storeLayout = storeLayoutRepository.get(StoreLayoutId.fromString(id));
      storeLayout.setOuterOutline(polygon);
      return StoreLayoutDtoFactory.createDto(storeLayout);
   }

   @Override
   public StoreLayoutDto updateInnerOutline(String id, GeoPolygon polygon) {
      StoreLayout storeLayout = storeLayoutRepository.get(StoreLayoutId.fromString(id));
      storeLayout.setInnerOutline(polygon);
      return StoreLayoutDtoFactory.createDto(storeLayout);
   }

   @Override
   public FeatureDto addFeature(String id, GeoPolygon polygon) {
      StoreLayout storeLayout = storeLayoutRepository.get(StoreLayoutId.fromString(id));
      Feature feature = storeLayout.addFeature(polygon);
      return FeatureDtoFactory.createDto(feature);
   }

   @Override
   public FeatureDto reshapeFeature(String id, String featureId, GeoPolygon polygon) {
      StoreLayout storeLayout = storeLayoutRepository.get(StoreLayoutId.fromString(id));
      Feature feature = storeLayout.reshapeFeature(FeatureId.fromString(featureId), polygon);
      return FeatureDtoFactory.createDto(feature);
   }

   @Override
   public StoreLayoutUpdateDto<NodeDto> addNode(String id, String type, GeoPoint location) {
      NodeModificationEventSubscriber nodeModificationEventSubscriber = new NodeModificationEventSubscriber();
      DomainEventPublisher.instance().subscribe(nodeModificationEventSubscriber);
      StoreLayout storeLayout = storeLayoutRepository.get(StoreLayoutId.fromString(id));
      Node node = storeLayout.addNode(NodeType.fromName(type), location);
      return StoreLayoutUpdateDtoFactory.createDto(storeLayout, NodeDtoFactory.createDto(node), nodeModificationEventSubscriber.getNodeIds());
   }

   @Override
   public NodeDto moveNode(String id, String nodeId, GeoPoint location) {
      StoreLayout storeLayout = storeLayoutRepository.get(StoreLayoutId.fromString(id));
      Node node = storeLayout.moveNode(NodeId.fromString(nodeId), location);
      return NodeDtoFactory.createDto(node);
   }

   @Override
   public StoreLayoutUpdateDto<NodeItemDto> addNodeItem(String id, String nodeId, CodeName item) {
      NodeModificationEventSubscriber nodeModificationEventSubscriber = new NodeModificationEventSubscriber();
      DomainEventPublisher.instance().subscribe(nodeModificationEventSubscriber);
      StoreLayout storeLayout = storeLayoutRepository.get(StoreLayoutId.fromString(id));
      NodeItem nodeItem = storeLayout.addNodeItem(NodeId.fromString(nodeId), new Item(item));
      return StoreLayoutUpdateDtoFactory.createDto(storeLayout, NodeItemDtoFactory.createDto(nodeItem), nodeModificationEventSubscriber.getNodeIds());
   }

   private static class NodeModificationEventSubscriber implements DomainEventSubscriber<NodeModifiedEvent> {

      private final List<String> nodeIds = new ArrayList<>();

      @Override
      public Class<NodeModifiedEvent> getSubscribedEventType() {
         return NodeModifiedEvent.class;
      }

      @Override
      public void handleEvent(NodeModifiedEvent domainEvent) {
         nodeIds.add(domainEvent.getNodeId());
      }

      public List<String> getNodeIds() {
         return nodeIds;
      }
   }
}
