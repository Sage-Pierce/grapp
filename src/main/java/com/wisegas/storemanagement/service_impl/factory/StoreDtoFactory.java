package com.wisegas.storemanagement.service_impl.factory;

import com.wisegas.storemanagement.domain.entity.Store;
import com.wisegas.storemanagement.service.dto.StoreDto;

public final class StoreDtoFactory {

   public static StoreDto createDto(Store store) {
      StoreDto storeDto = new StoreDto();
      storeDto.setId(store.getId().toString());
      storeDto.setName(store.getName());
      storeDto.setLocation(store.getLocation());
      storeDto.setLayoutID(store.getLayout().getId().toString());
      return storeDto;
   }

   private StoreDtoFactory() {

   }
}
