package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNode;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeDTO;

public final class GrappStoreNodeDTOFactory {

   public static GrappStoreNodeDTO createDTO(GrappStoreNode grappStoreNode) {
      GrappStoreNodeDTO grappStoreNodeDTO = new GrappStoreNodeDTO();
      grappStoreNodeDTO.setId(grappStoreNode.getId().toString());
      grappStoreNodeDTO.setName(grappStoreNode.getName());
      grappStoreNodeDTO.setType(grappStoreNode.getType().name());
      grappStoreNodeDTO.setLocation(grappStoreNode.getLocation());
      return grappStoreNodeDTO;
   }

   private GrappStoreNodeDTOFactory() {

   }
}
