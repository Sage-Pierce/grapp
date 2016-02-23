package com.wisegas.grapp.service_impl.factory;

import com.wisegas.grapp.domain.entity.GrappStore;
import com.wisegas.grapp.service.dto.GrappStoreDTO;

import java.util.ArrayList;
import java.util.List;

public final class GrappStoreDTOFactory {

   public static List<GrappStoreDTO> createDTOs(Iterable<GrappStore> grappStores) {
      List<GrappStoreDTO> grappStoreDTOs = new ArrayList<>();
      for (GrappStore grappStore : grappStores) {
         grappStoreDTOs.add(createDTO(grappStore));
      }
      return grappStoreDTOs;
   }

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
