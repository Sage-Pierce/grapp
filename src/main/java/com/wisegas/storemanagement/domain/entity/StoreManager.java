package com.wisegas.storemanagement.domain.entity;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.common.persistence.jpa.value.Email;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StoreManager extends SimpleEntity<Email> {
   @EmbeddedId
   private Email id;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "manager", orphanRemoval = true)
   private List<Store> stores = new ArrayList<>();

   public StoreManager(Email email) {
      id = email;
   }

   protected StoreManager() {

   }

   @Override
   public Email getId() {
      return id;
   }

   public List<Store> getStores() {
      return stores;
   }

   public Store addStore(String name, GeoPoint location) {
      Store store = new Store(this, name, location);
      stores.add(store);
      return store;
   }
}
