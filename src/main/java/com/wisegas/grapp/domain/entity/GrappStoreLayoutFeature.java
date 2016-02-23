package com.wisegas.grapp.domain.entity;

import com.wisegas.persistence.jpa.converter.GeoPolygonConverter;
import com.wisegas.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappStoreLayoutFeatureID;
import com.wisegas.value.GeoPolygon;

import javax.persistence.*;

@Entity
public class GrappStoreLayoutFeature extends SimpleEntity<GrappStoreLayoutFeatureID> {
   @Embedded
   private GrappStoreLayoutFeatureID id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStoreLayout grappStoreLayout;

   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon polygon;

   public GrappStoreLayoutFeature(GrappStoreLayout grappStoreLayout, GeoPolygon polygon) {
      id = GrappStoreLayoutFeatureID.generate();
      setGrappStoreLayout(grappStoreLayout);
      setPolygon(polygon);
   }

   protected GrappStoreLayoutFeature() {

   }

   @Override
   public GrappStoreLayoutFeatureID getId() {
      return id;
   }

   public GeoPolygon getPolygon() {
      return polygon;
   }

   // Only should be called by containing Layout since it maintains spacial invariants
   protected void setPolygon(GeoPolygon polygon) {
      this.polygon = polygon;
   }

   private void setGrappStoreLayout(GrappStoreLayout grappStoreLayout) {
      this.grappStoreLayout = grappStoreLayout;
   }
}
