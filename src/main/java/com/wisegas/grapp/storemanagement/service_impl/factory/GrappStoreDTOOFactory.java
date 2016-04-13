package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStore;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreDTOO;

public final class GrappStoreDTOOFactory {

   public static GrappStoreDTOO createDTO(GrappStore grappStore) {
      GrappStoreDTOO grappStoreDTO = new GrappStoreDTOO();
      grappStoreDTO.setId(grappStore.getId().toString());
      grappStoreDTO.setName(grappStore.getName());
      grappStoreDTO.setLocation(grappStore.getLocation());
      grappStoreDTO.setLayoutID(grappStore.getGrappStoreLayout().getId().toString());
      return grappStoreDTO;
   }

   private GrappStoreDTOOFactory() {

   }
}
