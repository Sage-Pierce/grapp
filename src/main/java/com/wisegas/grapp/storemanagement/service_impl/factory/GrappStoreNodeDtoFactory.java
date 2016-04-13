package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNode;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeDto;

public final class GrappStoreNodeDtoFactory {

   public static GrappStoreNodeDto createDto(GrappStoreNode grappStoreNode) {
      GrappStoreNodeDto grappStoreNodeDto = new GrappStoreNodeDto();
      grappStoreNodeDto.setId(grappStoreNode.getId().toString());
      grappStoreNodeDto.setName(grappStoreNode.getName());
      grappStoreNodeDto.setType(grappStoreNode.getType().name());
      grappStoreNodeDto.setLocation(grappStoreNode.getLocation());
      return grappStoreNodeDto;
   }

   private GrappStoreNodeDtoFactory() {

   }
}
