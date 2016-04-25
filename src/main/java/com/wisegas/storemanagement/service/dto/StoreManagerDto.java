package com.wisegas.storemanagement.service.dto;

import java.util.List;

public class StoreManagerDto {

   private String email;
   private List<StoreDto> stores;

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public List<StoreDto> getStores() {
      return stores;
   }

   public void setStores(List<StoreDto> stores) {
      this.stores = stores;
   }
}
