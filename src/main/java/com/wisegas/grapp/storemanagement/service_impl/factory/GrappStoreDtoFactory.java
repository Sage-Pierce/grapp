package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStore;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreDto;

public final class GrappStoreDtoFactory {

   public static GrappStoreDto createDTO(GrappStore grappStore) {
      GrappStoreDto grappStoreDTO = new GrappStoreDto();
      grappStoreDTO.setId(grappStore.getId().toString());
      grappStoreDTO.setName(grappStore.getName());
      grappStoreDTO.setLocation(grappStore.getLocation());
      grappStoreDTO.setLayoutID(grappStore.getGrappStoreLayout().getId().toString());
      return grappStoreDTO;
   }

   private GrappStoreDtoFactory() {

   }
}
