package org.codegas.stores.domain.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.codegas.common.domain.entity.SimpleEntity;
import org.codegas.common.lang.spacial.GeoPoint;
import org.codegas.common.lang.spacial.GeoPolygon;
import org.codegas.common.persistence.jpa.converter.GeoPolygonConverter;
import org.codegas.stores.domain.value.FeatureId;
import org.codegas.stores.domain.value.Item;
import org.codegas.stores.domain.value.NodeId;
import org.codegas.stores.domain.value.NodeType;
import org.codegas.stores.domain.value.StoreLayoutId;

@Entity
public class StoreLayout extends SimpleEntity<StoreLayoutId> {

    @EmbeddedId
    private StoreLayoutId id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private Store store;

    @Column(length = 2047)
    @Convert(converter = GeoPolygonConverter.class)
    private GeoPolygon outerOutline;

    @Column(length = 2047)
    @Convert(converter = GeoPolygonConverter.class)
    private GeoPolygon innerOutline;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "storeLayout", orphanRemoval = true)
    @MapKey(name = "id")
    private Map<FeatureId, Feature> features = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "storeLayout", orphanRemoval = true)
    @MapKey(name = "id")
    private Map<NodeId, Node> nodes = new HashMap<>();

    public StoreLayout(Store store) {
        id = StoreLayoutId.generate();
        setStore(store);
    }

    protected StoreLayout() {

    }

    @Override
    public StoreLayoutId getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public GeoPolygon getOuterOutline() {
        return outerOutline;
    }

    public void setOuterOutline(GeoPolygon outerPolygon) {
        this.outerOutline = outerPolygon;
    }

    public GeoPolygon getInnerOutline() {
        return innerOutline;
    }

    public void setInnerOutline(GeoPolygon innerPolygon) {
        this.innerOutline = innerPolygon;
    }

    public Collection<Feature> getFeatures() {
        return features.values();
    }

    public Feature reshapeFeature(FeatureId featureId, GeoPolygon polygon) {
        Feature feature = requireFeatureExistence(featureId);
        feature.setPolygon(polygon);
        return feature;
    }

    public Feature addFeature(GeoPolygon polygon) {
        Feature feature = new Feature(this, polygon);
        features.put(feature.getId(), feature);
        return feature;
    }

    public Node getNode(NodeId nodeId) {
        return nodes.get(nodeId);
    }

    public Node getNodeOfType(NodeType type) {
        return nodes.values().stream()
            .filter(node -> node.getType() == type)
            .findAny()
            .orElseThrow(() -> new IllegalStateException(String.format("Could not find node of Type %s in StoreLayout (%s)", type.name(), getId())));
    }

    public Collection<Node> getNodes() {
        return nodes.values();
    }

    public Node moveNode(NodeId nodeId, GeoPoint location) {
        Node node = requireNodeExistence(nodeId);
        node.setLocation(location);
        return node;
    }

    public Node addNode(NodeType type, GeoPoint location) {
        if (type.isSingleton()) {
            ensureNoNodesOfType(type);
        }
        Node node = new Node(this, "Node #" + nodes.size(), type, location);
        nodes.put(node.getId(), node);
        return node;
    }

    public NodeItem addNodeItem(NodeId nodeId, Item item) {
        Node node = requireNodeExistence(nodeId);
        ensureNodeItemUniqueness(node, item);
        return node.addItem(item);
    }

    private Feature requireFeatureExistence(FeatureId featureId) {
        return Objects.requireNonNull(features.get(featureId), () -> String.format("Feature (%s) not found in StoreLayout (%s).", featureId, getId()));
    }

    private Node requireNodeExistence(NodeId nodeId) {
        return Objects.requireNonNull(nodes.get(nodeId), () -> String.format("Node (%s) not found in StoreLayout (%s).", nodeId, getId()));
    }

    private void ensureNoNodesOfType(NodeType type) {
        for (Node node : nodes.values()) {
            if (node.getType() == type) {
                node.setType(NodeType.defaultNonSingleton());
            }
        }
    }

    private void ensureNodeItemUniqueness(Node node, Item item) {
        Optional<Node> foundNode = findNodeByItem(item);
        if (foundNode.isPresent() && !Objects.equals(node, foundNode.get())) {
            foundNode.get().removeItem(item);
        }
    }

    private Optional<Node> findNodeByItem(Item item) {
        return nodes.values().stream().filter(node -> node.containsItem(item)).findAny();
    }

    private void setStore(Store store) {
        this.store = store;
    }
}
