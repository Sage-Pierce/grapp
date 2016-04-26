package com.wisegas.storemanagement.service_impl.api_impl;

import com.wisegas.common.domain.model.DomainEventPublisher;
import com.wisegas.common.domain.model.DomainEventSubscriber;
import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.common.lang.spacial.GeoPolygon;
import com.wisegas.common.lang.value.CodeName;
import com.wisegas.storemanagement.domain.entity.Feature;
import com.wisegas.storemanagement.domain.entity.Layout;
import com.wisegas.storemanagement.domain.entity.Node;
import com.wisegas.storemanagement.domain.entity.NodeItem;
import com.wisegas.storemanagement.domain.event.NodeModifiedEvent;
import com.wisegas.storemanagement.domain.repository.LayoutRepository;
import com.wisegas.storemanagement.domain.value.*;
import com.wisegas.storemanagement.service.api.LayoutService;
import com.wisegas.storemanagement.service.dto.*;
import com.wisegas.storemanagement.service_impl.factory.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Named
@Singleton
@Transactional
@ApplicationService
public class LayoutServiceImpl implements LayoutService {

   private final LayoutRepository layoutRepository;

   @Inject
   public LayoutServiceImpl(LayoutRepository layoutRepository) {
      this.layoutRepository = layoutRepository;
   }

   @Override
   public LayoutDto get(String id) {
      return LayoutDtoFactory.createDto(layoutRepository.get(LayoutId.fromString(id)));
   }

   @Override
   public LayoutDto updateOuterOutline(String id, GeoPolygon outerPolygon) {
      Layout layout = layoutRepository.get(LayoutId.fromString(id));
      layout.setOuterOutline(outerPolygon);
      return LayoutDtoFactory.createDto(layout);
   }

   @Override
   public LayoutDto updateInnerOutline(String id, GeoPolygon innerPolygon) {
      Layout layout = layoutRepository.get(LayoutId.fromString(id));
      layout.setInnerOutline(innerPolygon);
      return LayoutDtoFactory.createDto(layout);
   }

   @Override
   public FeatureDto addFeature(String id, GeoPolygon polygon) {
      Layout layout = layoutRepository.get(LayoutId.fromString(id));
      Feature feature = layout.addFeature(polygon);
      return FeatureDtoFactory.createDto(feature);
   }

   @Override
   public FeatureDto reshapeFeature(String id, String featureId, GeoPolygon polygon) {
      Layout layout = layoutRepository.get(LayoutId.fromString(id));
      Feature feature = layout.reshapeFeature(FeatureId.fromString(featureId), polygon);
      return FeatureDtoFactory.createDto(feature);
   }

   @Override
   public LayoutUpdateDto<NodeDto> addNode(String id, String type, GeoPoint location) {
      NodeModificationEventSubscriber nodeModificationEventSubscriber = new NodeModificationEventSubscriber();
      DomainEventPublisher.instance().subscribe(nodeModificationEventSubscriber);
      Layout layout = layoutRepository.get(LayoutId.fromString(id));
      Node node = layout.addNode(NodeType.fromName(type), location);
      return LayoutUpdateDtoFactory.createDto(layout, NodeDtoFactory.createDto(node), nodeModificationEventSubscriber.getNodeIds());
   }

   @Override
   public NodeDto moveNode(String id, String nodeId, GeoPoint location) {
      Layout layout = layoutRepository.get(LayoutId.fromString(id));
      Node node = layout.moveNode(NodeId.fromString(nodeId), location);
      return NodeDtoFactory.createDto(node);
   }

   @Override
   public LayoutUpdateDto<NodeItemDto> addNodeItem(String id, String nodeId, CodeName item) {
      NodeModificationEventSubscriber nodeModificationEventSubscriber = new NodeModificationEventSubscriber();
      DomainEventPublisher.instance().subscribe(nodeModificationEventSubscriber);
      Layout layout = layoutRepository.get(LayoutId.fromString(id));
      NodeItem nodeItem = layout.addNodeItem(NodeId.fromString(nodeId), new Item(item));
      return LayoutUpdateDtoFactory.createDto(layout, NodeItemDtoFactory.createDto(nodeItem), nodeModificationEventSubscriber.getNodeIds());
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
