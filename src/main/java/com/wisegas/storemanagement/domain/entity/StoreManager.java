package com.wisegas.storemanagement.domain.entity;

import com.wisegas.common.lang.entity.SimpleEntity;
import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.common.lang.value.Email;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StoreManager extends SimpleEntity<Email> {
   @Id
   private String email;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "manager", orphanRemoval = true)
   private List<Store> stores = new ArrayList<>();

   public StoreManager(Email email) {
      this.email = email.toString();
   }

   protected StoreManager() {

   }

   @Override
   public Email getId() {
      return getEmail();
   }

   public Email getEmail() {
      return Email.fromString(email);
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
