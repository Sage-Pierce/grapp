package com.wisegas.stores.service_impl.factory;

import com.wisegas.stores.domain.entity.StoreManager;
import com.wisegas.stores.service.dto.StoreManagerDto;

import java.util.stream.Collectors;

public final class StoreManagerDtoFactory {

   private StoreManagerDtoFactory() {

   }

   public static StoreManagerDto createDto(StoreManager storeManager) {
      StoreManagerDto storeManagerDto = new StoreManagerDto();
      storeManagerDto.setEmail(storeManager.getEmail().toString());
      storeManagerDto.setStores(storeManager.getStores().stream().map(StoreDtoFactory::createDto).collect(Collectors.toList()));
      return storeManagerDto;
   }
}
