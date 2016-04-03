package com.wisegas.grapp.domain.entity;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.common.persistence.jpa.converter.GeoPolygonConverter;
import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappStoreFeatureIDFUCK;
import com.wisegas.grapp.domain.value.GrappStoreLayoutIDFUCK;
import com.wisegas.grapp.domain.value.GrappStoreNodeIDFUCK;
import com.wisegas.grapp.domain.value.GrappStoreNodeType;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class GrappStoreLayout extends SimpleEntity<GrappStoreLayoutIDFUCK> {
   @EmbeddedId
   private GrappStoreLayoutIDFUCK id;

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
   private Map<GrappStoreFeatureIDFUCK, GrappStoreFeature> features = new HashMap<>();

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreLayout", orphanRemoval = true)
   @MapKey(name = "id")
   private Map<GrappStoreNodeIDFUCK, GrappStoreNode> nodes = new HashMap<>();

   public GrappStoreLayout(GrappStore grappStore) {
      id = GrappStoreLayoutIDFUCK.generate();
      setGrappStore(grappStore);
   }

   protected GrappStoreLayout() {

   }

   @Override
   public GrappStoreLayoutIDFUCK getId() {
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

   public GrappStoreFeature reshapeFeature(GrappStoreFeatureIDFUCK grappStoreFeatureIDFUCK, GeoPolygon polygon) {
      if (features.containsKey(grappStoreFeatureIDFUCK)) {
         GrappStoreFeature grappStoreFeature = features.get(grappStoreFeatureIDFUCK);
         grappStoreFeature.setPolygon(polygon);
         return grappStoreFeature;
      }
      else {
         throw new RuntimeException(String.format("Feature (%s) not found in Layout (%s).", grappStoreFeatureIDFUCK, getId()));
      }
   }

   public GrappStoreFeature addFeature(GeoPolygon polygon) {
      GrappStoreFeature feature = new GrappStoreFeature(this, polygon);
      features.put(feature.getId(), feature);
      return feature;
   }

   public void removeFeature(GrappStoreFeatureIDFUCK grappStoreFeatureIDFUCK) {
      if (features.containsKey(grappStoreFeatureIDFUCK)) {
         features.remove(grappStoreFeatureIDFUCK);
      }
      else {
         throw new RuntimeException(String.format("Feature (%s) not found in Layout (%s).", grappStoreFeatureIDFUCK, getId()));
      }
   }

   public GrappStoreNode getNode(GrappStoreNodeIDFUCK grappStoreNodeIDFUCK) {
      return nodes.get(grappStoreNodeIDFUCK);
   }

   public Collection<GrappStoreNode> getNodes() {
      return nodes.values();
   }

   public GrappStoreNode moveNode(GrappStoreNodeIDFUCK grappStoreNodeIDFUCK, GeoPoint location) {
      if (nodes.containsKey(grappStoreNodeIDFUCK)) {
         GrappStoreNode grappStoreNode = nodes.get(grappStoreNodeIDFUCK);
         grappStoreNode.setLocation(location);
         return grappStoreNode;
      }
      else {
         throw new RuntimeException(String.format("Node (%s) not found in Layout (%s).", grappStoreNodeIDFUCK, getId()));
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

   public void removeNode(GrappStoreNodeIDFUCK grappStoreNodeIDFUCK) {
      if (nodes.containsKey(grappStoreNodeIDFUCK)) {
         nodes.remove(grappStoreNodeIDFUCK);
      }
      else {
         throw new RuntimeException(String.format("Node ($s) not found in Layout (%s).", grappStoreNodeIDFUCK, getId()));
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
