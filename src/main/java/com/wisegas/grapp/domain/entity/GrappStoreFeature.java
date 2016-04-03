package com.wisegas.grapp.domain.entity;

import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.common.persistence.jpa.converter.GeoPolygonConverter;
import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappStoreFeatureIDFUCK;

import javax.persistence.*;

@Entity
public class GrappStoreFeature extends SimpleEntity<GrappStoreFeatureIDFUCK> {
   @EmbeddedId
   private GrappStoreFeatureIDFUCK id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStoreLayout grappStoreLayout;

   @Column(length = 2047)
   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon polygon;

   public GrappStoreFeature(GrappStoreLayout grappStoreLayout, GeoPolygon polygon) {
      id = GrappStoreFeatureIDFUCK.generate();
      setGrappStoreLayout(grappStoreLayout);
      setPolygon(polygon);
   }

   protected GrappStoreFeature() {

   }

   @Override
   public GrappStoreFeatureIDFUCK getId() {
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
