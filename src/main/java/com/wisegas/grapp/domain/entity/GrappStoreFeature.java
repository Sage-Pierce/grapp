package com.wisegas.grapp.domain.entity;

import com.wisegas.persistence.jpa.converter.GeoPolygonConverter;
import com.wisegas.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.domain.value.GrappStoreFeatureID;
import com.wisegas.lang.GeoPolygon;

import javax.persistence.*;

@Entity
@Table(name = "\"GrappStoreFeature\"")
public class GrappStoreFeature extends SimpleEntity<GrappStoreFeatureID> {
   @EmbeddedId
   private GrappStoreFeatureID id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private GrappStoreLayout grappStoreLayout;

   @Column(length = 2047)
   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon polygon;

   public GrappStoreFeature(GrappStoreLayout grappStoreLayout, GeoPolygon polygon) {
      id = GrappStoreFeatureID.generate();
      setGrappStoreLayout(grappStoreLayout);
      setPolygon(polygon);
   }

   protected GrappStoreFeature() {

   }

   @Override
   public GrappStoreFeatureID getId() {
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
