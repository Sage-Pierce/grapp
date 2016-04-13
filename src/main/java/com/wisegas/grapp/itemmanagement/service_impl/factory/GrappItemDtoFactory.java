package com.wisegas.grapp.itemmanagement.service_impl.factory;

import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem;
import com.wisegas.grapp.itemmanagement.service.dto.GrappItemDto;

import java.util.stream.Collectors;

public final class GrappItemDtoFactory {

   public static GrappItemDto createDto(GrappItem grappItem) {
      GrappItemDto grappItemDTO = new GrappItemDto();
      grappItemDTO.setId(grappItem.getId().toString());
      grappItemDTO.setSuperItemId(grappItem.isGeneralItem() ? null : grappItem.getSuperItem().getName());
      grappItemDTO.setName(grappItem.getName());
      grappItemDTO.setSubItems(grappItem.getSubItems().stream().map(GrappItemDtoFactory::createDto).collect(Collectors.toList()));
      return grappItemDTO;
   }

   private GrappItemDtoFactory() {

   }
}
