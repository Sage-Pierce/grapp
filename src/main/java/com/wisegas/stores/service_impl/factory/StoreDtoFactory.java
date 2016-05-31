package com.wisegas.stores.service_impl.factory;

import com.wisegas.stores.domain.entity.Store;
import com.wisegas.stores.service.dto.StoreDto;

public final class StoreDtoFactory {

   public static StoreDto createDto(Store store) {
      StoreDto storeDto = new StoreDto();
      storeDto.setId(store.getId().toString());
      storeDto.setName(store.getName());
      storeDto.setLocation(store.getLocation());
      storeDto.setLayoutId(store.getStoreLayout().getId().toString());
      return storeDto;
   }

   private StoreDtoFactory() {

   }
}
