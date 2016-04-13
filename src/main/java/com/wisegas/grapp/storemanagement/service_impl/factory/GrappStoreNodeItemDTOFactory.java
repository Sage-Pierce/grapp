package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNodeItem;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeItemDTOO;

public final class GrappStoreNodeItemDTOFactory {

   public static GrappStoreNodeItemDTOO createDTO(GrappStoreNodeItem grappStoreNodeItem) {
      GrappStoreNodeItemDTOO grappStoreNodeItemDTO = new GrappStoreNodeItemDTOO();
      grappStoreNodeItemDTO.setId(grappStoreNodeItem.getId().toString());
      grappStoreNodeItemDTO.setItem(grappStoreNodeItem.getItem().toCodeName());
      return grappStoreNodeItemDTO;
   }

   private GrappStoreNodeItemDTOFactory() {

   }
}
