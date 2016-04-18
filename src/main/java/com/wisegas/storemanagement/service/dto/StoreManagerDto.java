package com.wisegas.storemanagement.service.dto;

import com.wisegas.common.lang.dto.BaseDto;

import java.util.List;

public class StoreManagerDto extends BaseDto {

   private List<StoreDto> stores;

   public List<StoreDto> getStores() {
      return stores;
   }

   public void setStores(List<StoreDto> stores) {
      this.stores = stores;
   }
}
