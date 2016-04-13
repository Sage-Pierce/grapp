package com.wisegas.grapp.itemmanagement.service_impl.factory;

import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem;
import com.wisegas.grapp.itemmanagement.service.dto.GrappItemDto;

import java.util.stream.Collectors;

public final class GrappItemDtoFactory {

   public static GrappItemDto createDto(GrappItem grappItem) {
      GrappItemDto grappItemDto = new GrappItemDto();
      grappItemDto.setId(grappItem.getId().toString());
      grappItemDto.setSuperItemId(grappItem.isGeneralItem() ? null : grappItem.getSuperItem().getName());
      grappItemDto.setName(grappItem.getName());
      grappItemDto.setSubItems(grappItem.getSubItems().stream().map(GrappItemDtoFactory::createDto).collect(Collectors.toList()));
      return grappItemDto;
   }

   private GrappItemDtoFactory() {

   }
}
