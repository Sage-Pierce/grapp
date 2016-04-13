package com.wisegas.grapp.storemanagement.domain.entity;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.common.persistence.jpa.converter.GeoPolygonConverter;
import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.storemanagement.domain.value.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class GrappStoreLayout extends SimpleEntity<GrappStoreLayoutId> {
   @EmbeddedId
   private GrappStoreLayoutId id;

   @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStore grappStore;

   @Column(length = 2047)
   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon outerOutline;

   @Column(length = 2047)
   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon innerOutline;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreLayout", orphanRemoval = true)
   @MapKey(name = "id")
   private Map<GrappStoreFeatureId, GrappStoreFeature> features = new HashMap<>();

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreLayout", orphanRemoval = true)
   @MapKey(name = "id")
   private Map<GrappStoreNodeId, GrappStoreNode> nodes = new HashMap<>();

   public GrappStoreLayout(GrappStore grappStore) {
      id = GrappStoreLayoutId.generate();
      setGrappStore(grappStore);
   }

   protected GrappStoreLayout() {

   }

   @Override
   public GrappStoreLayoutId getId() {
      return id;
   }

   public GrappStore getGrappStore() {
      return grappStore;
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

   public Collection<GrappStoreFeature> getFeatures() {
      return features.values();
   }

   public GrappStoreFeature reshapeFeature(GrappStoreFeatureId grappStoreFeatureId, GeoPolygon polygon) {
      GrappStoreFeature grappStoreFeature = requireFeatureExistence(grappStoreFeatureId);
      grappStoreFeature.setPolygon(polygon);
      return grappStoreFeature;
   }

   public GrappStoreFeature addFeature(GeoPolygon polygon) {
      GrappStoreFeature feature = new GrappStoreFeature(this, polygon);
      features.put(feature.getId(), feature);
      return feature;
   }

   public void removeFeature(GrappStoreFeatureId grappStoreFeatureId) {
      requireFeatureExistence(grappStoreFeatureId);
      features.remove(grappStoreFeatureId);
   }

   public GrappStoreNode getNode(GrappStoreNodeId grappStoreNodeId) {
      return nodes.get(grappStoreNodeId);
   }

   public Collection<GrappStoreNode> getNodes() {
      return nodes.values();
   }

   public GrappStoreNode moveNode(GrappStoreNodeId grappStoreNodeId, GeoPoint location) {
      GrappStoreNode grappStoreNode = requireNodeExistence(grappStoreNodeId);
      grappStoreNode.setLocation(location);
      return grappStoreNode;
   }

   public GrappStoreNode addNode(GrappStoreNodeType type, GeoPoint location) {
      if (type.isSingleton()) {
         ensureNoNodesOfType(type);
      }
      GrappStoreNode node = new GrappStoreNode(this, "Grapp Store Node #" + nodes.size(), type, location);
      nodes.put(node.getId(), node);
      return node;
   }

   public void removeNode(GrappStoreNodeId grappStoreNodeId) {
      requireNodeExistence(grappStoreNodeId);
      nodes.remove(grappStoreNodeId);
   }

   public GrappStoreNodeItem addNodeItem(GrappStoreNodeId nodeId, Item item) {
      GrappStoreNode node = requireNodeExistence(nodeId);
      ensureNodeItemUniqueness(node, item);
      return node.addItem(item);
   }

   private GrappStoreFeature requireFeatureExistence(GrappStoreFeatureId featureId) {
      return Objects.requireNonNull(features.get(featureId), () -> String.format("Feature (%s) not found in Layout (%s).", featureId, getId()));
   }

   private GrappStoreNode requireNodeExistence(GrappStoreNodeId nodeId) {
      return Objects.requireNonNull(nodes.get(nodeId), () -> String.format("Node (%s) not found in Layout (%s).", nodeId, getId()));
   }

   private void ensureNoNodesOfType(GrappStoreNodeType type) {
      for (GrappStoreNode node : nodes.values()) {
         if (node.getType() == type) {
            node.setType(GrappStoreNodeType.defaultNonSingleton());
         }
      }
   }

   private void ensureNodeItemUniqueness(GrappStoreNode node, Item item) {
      Optional<GrappStoreNode> foundNode = findNodeByItem(item);
      if (foundNode.isPresent() && !Objects.equals(node, foundNode.get())) {
         foundNode.get().removeItem(item);
      }
   }

   private Optional<GrappStoreNode> findNodeByItem(Item item) {
      return nodes.values().stream().filter(node -> node.containsItem(item)).findAny();
   }

   private void setGrappStore(GrappStore grappStore) {
      this.grappStore = grappStore;
   }
}
