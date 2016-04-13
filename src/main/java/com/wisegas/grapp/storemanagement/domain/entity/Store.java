package com.wisegas.grapp.storemanagement.domain.entity;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.persistence.jpa.converter.GeoPointConverter;
import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.storemanagement.domain.value.StoreId;

import javax.persistence.*;

@Entity
public class Store extends NamedEntity<StoreId> {
   @EmbeddedId
   private StoreId id;

   @Column(length = 63)
   @Convert(converter = GeoPointConverter.class)
   private GeoPoint location;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "store", orphanRemoval = true)
   private Layout layout = new Layout(this);

   public Store(String name, GeoPoint location) {
      id = StoreId.generate();
      setName(name);
      setLocation(location);
   }

   protected Store() {

   }

   @Override
   public StoreId getId() {
      return id;
   }

   public GeoPoint getLocation() {
      return location;
   }

   public void setLocation(GeoPoint location) {
      this.location = location;
   }

   public Layout getLayout() {
      return layout;
   }
}
