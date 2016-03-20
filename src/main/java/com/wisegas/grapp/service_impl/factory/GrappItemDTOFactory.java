package com.wisegas.grapp.service_impl.factory;

import com.wisegas.grapp.domain.entity.GrappItem;
import com.wisegas.grapp.service.dto.GrappItemDTO;

import java.util.stream.Collectors;

public final class GrappItemDTOFactory {

   public static GrappItemDTO createDTO(GrappItem grappItem) {
      GrappItemDTO grappItemDTO = new GrappItemDTO();
      grappItemDTO.setId(grappItem.getId().toString());
      grappItemDTO.setName(grappItem.getName());
      grappItemDTO.setSuperItemId(grappItem.isGeneralItem() ? null : grappItem.getSuperItem().getId().toString());
      grappItemDTO.setSubItems(grappItem.getSubItems().stream().map(GrappItemDTOFactory::createDTO).collect(Collectors.toList()));
      return grappItemDTO;
   }

   private GrappItemDTOFactory() {

   }
}
