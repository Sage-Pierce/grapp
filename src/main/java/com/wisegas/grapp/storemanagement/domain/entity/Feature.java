package com.wisegas.grapp.storemanagement.domain.entity;

import com.wisegas.common.lang.value.GeoPolygon;
import com.wisegas.common.persistence.jpa.converter.GeoPolygonConverter;
import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.storemanagement.domain.value.FeatureId;

import javax.persistence.*;

@Entity
public class Feature extends SimpleEntity<FeatureId> {
   @EmbeddedId
   private FeatureId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private Layout layout;

   @Column(length = 2047)
   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon polygon;

   public Feature(Layout layout, GeoPolygon polygon) {
      id = FeatureId.generate();
      setLayout(layout);
      setPolygon(polygon);
   }

   protected Feature() {

   }

   @Override
   public FeatureId getId() {
      return id;
   }

   public GeoPolygon getPolygon() {
      return polygon;
   }

   // Only should be called by containing Layout since it maintains spacial invariants
   protected void setPolygon(GeoPolygon polygon) {
      this.polygon = polygon;
   }

   private void setLayout(Layout layout) {
      this.layout = layout;
   }
}
