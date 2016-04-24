package com.wisegas.storemanagement.domain.entity;

import com.wisegas.common.lang.entity.SimpleEntity;
import com.wisegas.common.lang.value.Email;
import com.wisegas.common.lang.value.GeoPoint;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StoreManager extends SimpleEntity<Email> {
   @Id
   private String id;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "manager", orphanRemoval = true)
   private List<Store> stores = new ArrayList<>();

   public StoreManager(Email email) {
      id = email.toString();
   }

   protected StoreManager() {

   }

   @Override
   public Email getId() {
      return Email.fromString(id);
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
