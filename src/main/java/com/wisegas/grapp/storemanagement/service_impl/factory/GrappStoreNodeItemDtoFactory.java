package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNodeItem;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeItemDto;

public final class GrappStoreNodeItemDtoFactory {

   public static GrappStoreNodeItemDto createDto(GrappStoreNodeItem grappStoreNodeItem) {
      GrappStoreNodeItemDto grappStoreNodeItemDto = new GrappStoreNodeItemDto();
      grappStoreNodeItemDto.setId(grappStoreNodeItem.getId().toString());
      grappStoreNodeItemDto.setItem(grappStoreNodeItem.getItem().toCodeName());
      return grappStoreNodeItemDto;
   }

   private GrappStoreNodeItemDtoFactory() {

   }
}
