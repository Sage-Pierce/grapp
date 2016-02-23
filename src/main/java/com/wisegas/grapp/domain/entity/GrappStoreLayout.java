package com.wisegas.grapp.domain.entity;

import com.wisegas.persistence.jpa.converter.GeoPolygonConverter;
import com.wisegas.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappStoreLayoutFeatureID;
import com.wisegas.grapp.domain.value.GrappStoreLayoutID;
import com.wisegas.value.GeoPolygon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Entity
public class GrappStoreLayout extends SimpleEntity<GrappStoreLayoutID> {
   @Embedded
   private GrappStoreLayoutID id;

   @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStore grappStore;

   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon outerOutline;

   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon innerOutline;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grappStoreLayout", orphanRemoval = true)
   private List<GrappStoreLayoutFeature> features = new ArrayList<>();

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
      for (GrappStoreLayoutFeature feature : features) {
         if (feature.getId().equals(grappStoreLayoutFeatureID)) {
            feature.setPolygon(polygon);
            return feature;
         }
      }
      throw new RuntimeException(String.format("Feature (%s) not found in Layout (%s).", grappStoreLayoutFeatureID, getId()));
   }

   public Collection<GrappStoreLayoutFeature> getFeatures() {
      return features;
   }

   public GrappStoreLayoutFeature addFeature(GeoPolygon polygon) {
      GrappStoreLayoutFeature feature = new GrappStoreLayoutFeature(this, polygon);
      features.add(feature);
      return feature;
   }

   public void removeFeature(GrappStoreLayoutFeatureID grappStoreLayoutFeatureID) {
      for (Iterator<GrappStoreLayoutFeature> iterator = features.iterator(); iterator.hasNext();) {
         GrappStoreLayoutFeature feature = iterator.next();
         if (feature.getId().equals(grappStoreLayoutFeatureID)) {
            iterator.remove();
            return;
         }
      }
      throw new RuntimeException(String.format("Feature (%s) not found in Layout (%s).", grappStoreLayoutFeatureID, getId()));
   }

   private void setGrappStore(GrappStore grappStore) {
      this.grappStore = grappStore;
   }
}
