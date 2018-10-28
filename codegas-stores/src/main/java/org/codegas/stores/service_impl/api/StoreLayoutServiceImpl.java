package org.codegas.stores.service_impl.api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.domain.event.DomainEventPublisher;
import org.codegas.commons.domain.event.DomainEventSubscriber;
import org.codegas.commons.lang.annotation.ApplicationService;
import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.commons.lang.spacial.GeoPolygon;
import org.codegas.commons.lang.value.CodeName;
import org.codegas.stores.domain.entity.Feature;
import org.codegas.stores.domain.entity.Node;
import org.codegas.stores.domain.entity.NodeItem;
import org.codegas.stores.domain.entity.StoreLayout;
import org.codegas.stores.domain.event.NodeModifiedEvent;
import org.codegas.stores.domain.repository.StoreLayoutRepository;
import org.codegas.stores.domain.value.FeatureId;
import org.codegas.stores.domain.value.Item;
import org.codegas.stores.domain.value.NodeId;
import org.codegas.stores.domain.value.NodeType;
import org.codegas.stores.domain.value.StoreLayoutId;
import org.codegas.stores.service.api.StoreLayoutService;
import org.codegas.stores.service.dto.FeatureDto;
import org.codegas.stores.service.dto.NodeDto;
import org.codegas.stores.service.dto.NodeItemDto;
import org.codegas.stores.service.dto.StoreLayoutDto;
import org.codegas.stores.service.dto.StoreLayoutUpdateDto;
import org.codegas.stores.service_impl.factory.FeatureDtoFactory;
import org.codegas.stores.service_impl.factory.NodeDtoFactory;
import org.codegas.stores.service_impl.factory.NodeItemDtoFactory;
import org.codegas.stores.service_impl.factory.StoreLayoutDtoFactory;
import org.codegas.stores.service_impl.factory.StoreLayoutUpdateDtoFactory;

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
        Node node = storeLayout.addNode(NodeType.valueOf(type), location);
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
