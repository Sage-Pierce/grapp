package com.wisegas.storemanagement.domain.entity;

import com.wisegas.common.lang.entity.SimpleEntity;
import com.wisegas.common.lang.spacial.GeoPolygon;
import com.wisegas.common.persistence.jpa.converter.GeoPolygonConverter;
import com.wisegas.storemanagement.domain.value.FeatureId;

import javax.persistence.*;

@Entity
public class Feature extends SimpleEntity<FeatureId> {
   @EmbeddedId
   private FeatureId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private StoreLayout storeLayout;

   @Column(length = 2047)
   @Convert(converter = GeoPolygonConverter.class)
   private GeoPolygon polygon;

   public Feature(StoreLayout storeLayout, GeoPolygon polygon) {
      id = FeatureId.generate();
      setStoreLayout(storeLayout);
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

   // Only should be called by containing StoreLayout since it maintains spacial invariants
   protected void setPolygon(GeoPolygon polygon) {
      this.polygon = polygon;
   }

   private void setStoreLayout(StoreLayout storeLayout) {
      this.storeLayout = storeLayout;
   }
}
