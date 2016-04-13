package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNode;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeDTOO;

public final class GrappStoreNodeDTOOFactory {

   public static GrappStoreNodeDTOO createDTO(GrappStoreNode grappStoreNode) {
      GrappStoreNodeDTOO grappStoreNodeDTO = new GrappStoreNodeDTOO();
      grappStoreNodeDTO.setId(grappStoreNode.getId().toString());
      grappStoreNodeDTO.setName(grappStoreNode.getName());
      grappStoreNodeDTO.setType(grappStoreNode.getType().name());
      grappStoreNodeDTO.setLocation(grappStoreNode.getLocation());
      return grappStoreNodeDTO;
   }

   private GrappStoreNodeDTOOFactory() {

   }
}
