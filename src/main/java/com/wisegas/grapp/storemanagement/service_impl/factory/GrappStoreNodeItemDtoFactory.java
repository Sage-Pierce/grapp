package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNodeItem;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeItemDto;

public final class GrappStoreNodeItemDtoFactory {

   public static GrappStoreNodeItemDto createDto(GrappStoreNodeItem grappStoreNodeItem) {
      GrappStoreNodeItemDto grappStoreNodeItemDTO = new GrappStoreNodeItemDto();
      grappStoreNodeItemDTO.setId(grappStoreNodeItem.getId().toString());
      grappStoreNodeItemDTO.setItem(grappStoreNodeItem.getItem().toCodeName());
      return grappStoreNodeItemDTO;
   }

   private GrappStoreNodeItemDtoFactory() {

   }
}
