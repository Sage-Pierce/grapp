package com.wisegas.storemanagement.service.dto;

import com.wisegas.common.lang.value.Email;

import java.util.List;

public class StoreManagerDto {

   private Email email;
   private List<StoreDto> stores;

   public Email getEmail() {
      return email;
   }

   public void setEmail(Email email) {
      this.email = email;
   }

   public List<StoreDto> getStores() {
      return stores;
   }

   public void setStores(List<StoreDto> stores) {
      this.stores = stores;
   }
}
