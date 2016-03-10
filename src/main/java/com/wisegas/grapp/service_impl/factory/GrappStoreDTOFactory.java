package com.wisegas.grapp.service_impl.factory;

import com.wisegas.grapp.domain.entity.GrappStore;
import com.wisegas.grapp.service.dto.GrappStoreDTO;

public final class GrappStoreDTOFactory {

   public static GrappStoreDTO createDTO(GrappStore grappStore) {
      GrappStoreDTO grappStoreDTO = new GrappStoreDTO();
      grappStoreDTO.setId(grappStore.getId().toString());
      grappStoreDTO.setName(grappStore.getName());
      grappStoreDTO.setLocation(grappStore.getLocation());
      grappStoreDTO.setLayoutID(grappStore.getGrappStoreLayout().getId().toString());
      return grappStoreDTO;
   }

   private GrappStoreDTOFactory() {

   }
}
