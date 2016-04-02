package com.wisegas.grapp.domain.entity;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.common.persistence.jpa.converter.GeoPolygonConverter;
import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappStoreFeatureID;
import com.wisegas.grapp.domain.value.GrappStoreLayoutID;
import com.wisegas.grapp.domain.value.GrappStoreNodeID;
import com.wisegas.grapp.domain.value.GrappStoreNodeType;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class GrappStoreLayout extends SimpleEntity<GrappStoreLayoutID> {
   @EmbeddedId
   private GrappStoreLayoutID id;

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
   private Map<GrappStoreFeatureID, GrappStoreFeature> features = new HashMap<>();

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreLayout", orphanRemoval = true)
   @MapKey(name = "id")
   private Map<GrappStoreNodeID, GrappStoreNode> nodes = new HashMap<>();

   public GrappStoreLayout(GrappStore grappStore) {
      id = GrappStoreLayoutID.generate();
      setGrappStore(grappStore);
   }

   protected GrappStoreLayout() {

   }

   @Override
   public GrappStoreLayoutID getId() {
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

   public GrappStoreFeature reshapeFeature(GrappStoreFeatureID grappStoreFeatureID, GeoPolygon polygon) {
      if (features.containsKey(grappStoreFeatureID)) {
         GrappStoreFeature grappStoreFeature = features.get(grappStoreFeatureID);
         grappStoreFeature.setPolygon(polygon);
         return grappStoreFeature;
      }
      else {
         throw new RuntimeException(String.format("Feature (%s) not found in Layout (%s).", grappStoreFeatureID, getId()));
      }
   }

   public GrappStoreFeature addFeature(GeoPolygon polygon) {
      GrappStoreFeature feature = new GrappStoreFeature(this, polygon);
      features.put(feature.getId(), feature);
      return feature;
   }

   public void removeFeature(GrappStoreFeatureID grappStoreFeatureID) {
      if (features.containsKey(grappStoreFeatureID)) {
         features.remove(grappStoreFeatureID);
      }
      else {
         throw new RuntimeException(String.format("Feature (%s) not found in Layout (%s).", grappStoreFeatureID, getId()));
      }
   }

   public GrappStoreNode getNode(GrappStoreNodeID grappStoreNodeID) {
      return nodes.get(grappStoreNodeID);
   }

   public Collection<GrappStoreNode> getNodes() {
      return nodes.values();
   }

   public GrappStoreNode moveNode(GrappStoreNodeID grappStoreNodeID, GeoPoint location) {
      if (nodes.containsKey(grappStoreNodeID)) {
         GrappStoreNode grappStoreNode = nodes.get(grappStoreNodeID);
         grappStoreNode.setLocation(location);
         return grappStoreNode;
      }
      else {
         throw new RuntimeException(String.format("Node (%s) not found in Layout (%s).", grappStoreNodeID, getId()));
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

   public void removeNode(GrappStoreNodeID grappStoreNodeID) {
      if (nodes.containsKey(grappStoreNodeID)) {
         nodes.remove(grappStoreNodeID);
      }
      else {
         throw new RuntimeException(String.format("Node ($s) not found in Layout (%s).", grappStoreNodeID, getId()));
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
