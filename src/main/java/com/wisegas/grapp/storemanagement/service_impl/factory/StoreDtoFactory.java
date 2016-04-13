package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.Store;
import com.wisegas.grapp.storemanagement.service.dto.StoreDto;

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
