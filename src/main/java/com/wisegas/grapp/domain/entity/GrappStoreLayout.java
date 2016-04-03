package com.wisegas.grapp.domain.entity;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.common.persistence.jpa.converter.GeoPolygonConverter;
import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappStoreFeatureId;
import com.wisegas.grapp.domain.value.GrappStoreLayoutId;
import com.wisegas.grapp.domain.value.GrappStoreNodeId;
import com.wisegas.grapp.domain.value.GrappStoreNodeType;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
      if (features.containsKey(grappStoreFeatureId)) {
         GrappStoreFeature grappStoreFeature = features.get(grappStoreFeatureId);
         grappStoreFeature.setPolygon(polygon);
         return grappStoreFeature;
      }
      else {
         throw new RuntimeException(String.format("Feature (%s) not found in Layout (%s).", grappStoreFeatureId, getId()));
      }
   }

   public GrappStoreFeature addFeature(GeoPolygon polygon) {
      GrappStoreFeature feature = new GrappStoreFeature(this, polygon);
      features.put(feature.getId(), feature);
      return feature;
   }

   public void removeFeature(GrappStoreFeatureId grappStoreFeatureId) {
      if (features.containsKey(grappStoreFeatureId)) {
         features.remove(grappStoreFeatureId);
      }
      else {
         throw new RuntimeException(String.format("Feature (%s) not found in Layout (%s).", grappStoreFeatureId, getId()));
      }
   }

   public GrappStoreNode getNode(GrappStoreNodeId grappStoreNodeId) {
      return nodes.get(grappStoreNodeId);
   }

   public Collection<GrappStoreNode> getNodes() {
      return nodes.values();
   }

   public GrappStoreNode moveNode(GrappStoreNodeId grappStoreNodeId, GeoPoint location) {
      if (nodes.containsKey(grappStoreNodeId)) {
         GrappStoreNode grappStoreNode = nodes.get(grappStoreNodeId);
         grappStoreNode.setLocation(location);
         return grappStoreNode;
      }
      else {
         throw new RuntimeException(String.format("Node (%s) not found in Layout (%s).", grappStoreNodeId, getId()));
      }
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
      if (nodes.containsKey(grappStoreNodeId)) {
         nodes.remove(grappStoreNodeId);
      }
      else {
         throw new RuntimeException(String.format("Node ($s) not found in Layout (%s).", grappStoreNodeId, getId()));
      }
   }

   private void ensureNoNodesOfType(GrappStoreNodeType type) {
      for (GrappStoreNode node : nodes.values()) {
         if (node.getType() == type) {
            node.setType(GrappStoreNodeType.defaultNonSingleton());
         }
      }
   }

   private void setGrappStore(GrappStore grappStore) {
      this.grappStore = grappStore;
   }
}
