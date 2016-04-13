package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNode;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeDto;

public final class GrappStoreNodeDtoFactory {

   public static GrappStoreNodeDto createDTO(GrappStoreNode grappStoreNode) {
      GrappStoreNodeDto grappStoreNodeDTO = new GrappStoreNodeDto();
      grappStoreNodeDTO.setId(grappStoreNode.getId().toString());
      grappStoreNodeDTO.setName(grappStoreNode.getName());
      grappStoreNodeDTO.setType(grappStoreNode.getType().name());
      grappStoreNodeDTO.setLocation(grappStoreNode.getLocation());
      return grappStoreNodeDTO;
   }

   private GrappStoreNodeDtoFactory() {

   }
}
