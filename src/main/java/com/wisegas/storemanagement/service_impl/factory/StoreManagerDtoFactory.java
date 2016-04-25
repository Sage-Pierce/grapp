package com.wisegas.storemanagement.service_impl.factory;

import com.wisegas.storemanagement.domain.entity.StoreManager;
import com.wisegas.storemanagement.service.dto.StoreManagerDto;

import java.util.stream.Collectors;

public final class StoreManagerDtoFactory {

   public static StoreManagerDto createDto(StoreManager storeManager) {
      StoreManagerDto storeManagerDto = new StoreManagerDto();
      storeManagerDto.setEmail(storeManager.getEmail().toString());
      storeManagerDto.setStores(storeManager.getStores().stream().map(StoreDtoFactory::createDto).collect(Collectors.toList()));
      return storeManagerDto;
   }

   private StoreManagerDtoFactory() {

   }
}
