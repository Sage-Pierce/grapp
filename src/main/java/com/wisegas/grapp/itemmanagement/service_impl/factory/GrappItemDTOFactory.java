package com.wisegas.grapp.itemmanagement.service_impl.factory;

import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem;
import com.wisegas.grapp.itemmanagement.service.dto.GrappItemDTOO;

import java.util.stream.Collectors;

public final class GrappItemDTOFactory {

   public static GrappItemDTOO createDTO(GrappItem grappItem) {
      GrappItemDTOO grappItemDTO = new GrappItemDTOO();
      grappItemDTO.setId(grappItem.getId().toString());
      grappItemDTO.setSuperItemId(grappItem.isGeneralItem() ? null : grappItem.getSuperItem().getName());
      grappItemDTO.setName(grappItem.getName());
      grappItemDTO.setSubItems(grappItem.getSubItems().stream().map(GrappItemDTOFactory::createDTO).collect(Collectors.toList()));
      return grappItemDTO;
   }

   private GrappItemDTOFactory() {

   }
}
