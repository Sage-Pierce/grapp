package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStore;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreDto;

public final class GrappStoreDtoFactory {

   public static GrappStoreDto createDto(GrappStore grappStore) {
      GrappStoreDto grappStoreDto = new GrappStoreDto();
      grappStoreDto.setId(grappStore.getId().toString());
      grappStoreDto.setName(grappStore.getName());
      grappStoreDto.setLocation(grappStore.getLocation());
      grappStoreDto.setLayoutID(grappStore.getGrappStoreLayout().getId().toString());
      return grappStoreDto;
   }

   private GrappStoreDtoFactory() {

   }
}
