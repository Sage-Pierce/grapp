package com.wisegas.grapp.domain.entity;

import com.wisegas.persistence.jpa.converter.GeoPolygonConverter;
import com.wisegas.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappStoreFeatureID;
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

   @Column(length = 2047)
   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon outerOutline;

   @Column(length = 2047)
   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon innerOutline;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreLayout", orphanRemoval = true)
   @MapKey(name = "id")
   private Map<GrappStoreFeatureID, GrappStoreFeature> features = new HashMap<>();

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

   public Collection<GrappStoreFeature> getFeatures() {
      return features.values();
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

   private void setGrappStore(GrappStore grappStore) {
      this.grappStore = grappStore;
   }
}
