package com.wisegas.grapp.domain.entity;

import com.wisegas.persistence.jpa.converter.GeoPolygonConverter;
import com.wisegas.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappStoreLayoutFeatureID;
import com.wisegas.grapp.domain.value.GrappStoreLayoutID;
import com.wisegas.lang.GeoPolygon;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "\"GrappStoreLayout\"")
public class GrappStoreLayout extends SimpleEntity<GrappStoreLayoutID> {
   @EmbeddedId
   private GrappStoreLayoutID id;

   @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStore grappStore;

   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon outerOutline;

   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon innerOutline;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreLayout", orphanRemoval = true)
   @MapKey(name = "id")
   private Map<GrappStoreLayoutFeatureID, GrappStoreLayoutFeature> features = new HashMap<>();

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

   public GrappStoreLayoutFeature reshapeFeature(GrappStoreLayoutFeatureID grappStoreLayoutFeatureID, GeoPolygon polygon) {
      if (features.containsKey(grappStoreLayoutFeatureID)) {
         GrappStoreLayoutFeature grappStoreLayoutFeature = features.get(grappStoreLayoutFeatureID);
         grappStoreLayoutFeature.setPolygon(polygon);
         return grappStoreLayoutFeature;
      }
      else {
         throw new RuntimeException(String.format("Feature (%s) not found in Layout (%s).", grappStoreLayoutFeatureID, getId()));
      }
   }

   public Collection<GrappStoreLayoutFeature> getFeatures() {
      return features.values();
   }

   public GrappStoreLayoutFeature addFeature(GeoPolygon polygon) {
      GrappStoreLayoutFeature feature = new GrappStoreLayoutFeature(this, polygon);
      features.put(feature.getId(), feature);
      return feature;
   }

   public void removeFeature(GrappStoreLayoutFeatureID grappStoreLayoutFeatureID) {
      if (features.containsKey(grappStoreLayoutFeatureID)) {
         features.remove(grappStoreLayoutFeatureID);
      }
      else {
         throw new RuntimeException(String.format("Feature (%s) not found in Layout (%s).", grappStoreLayoutFeatureID, getId()));
      }
   }

   private void setGrappStore(GrappStore grappStore) {
      this.grappStore = grappStore;
   }
}
