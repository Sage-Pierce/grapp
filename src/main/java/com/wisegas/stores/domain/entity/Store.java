package com.wisegas.stores.domain.entity;

import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.common.persistence.jpa.converter.GeoPointConverter;
import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.stores.domain.value.StoreId;

import javax.persistence.*;

@Entity
public class Store extends NamedEntity<StoreId> {
   @EmbeddedId
   private StoreId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private StoreManager manager;

   @Column(length = 63)
   @Convert(converter = GeoPointConverter.class)
   private GeoPoint location;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "store", orphanRemoval = true)
   private StoreLayout storeLayout = new StoreLayout(this);

   public Store(StoreManager manager, String name, GeoPoint location) {
      id = StoreId.generate();
      setManager(manager);
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

   public StoreLayout getStoreLayout() {
      return storeLayout;
   }

   private void setManager(StoreManager manager) {
      this.manager = manager;
   }
}