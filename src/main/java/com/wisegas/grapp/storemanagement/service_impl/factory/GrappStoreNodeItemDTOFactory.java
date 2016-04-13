package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNodeItem;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeItemDTO;

public final class GrappStoreNodeItemDTOFactory {

   public static GrappStoreNodeItemDTO createDTO(GrappStoreNodeItem grappStoreNodeItem) {
      GrappStoreNodeItemDTO grappStoreNodeItemDTO = new GrappStoreNodeItemDTO();
      grappStoreNodeItemDTO.setId(grappStoreNodeItem.getId().toString());
      grappStoreNodeItemDTO.setItem(grappStoreNodeItem.getItem().toCodeName());
      return grappStoreNodeItemDTO;
   }

   private GrappStoreNodeItemDTOFactory() {

   }
}
