package com.wisegas.itemmanagement.service_impl.factory;

import com.wisegas.common.lang.value.IdName;
import com.wisegas.itemmanagement.domain.entity.Item;
import com.wisegas.itemmanagement.service.dto.ItemLineageDto;

import java.util.stream.Collectors;

public final class ItemLineageDtoFactory {

   public static ItemLineageDto createDto(Item item) {
      ItemLineageDto itemLineageDto = new ItemLineageDto();
      itemLineageDto.setPrimaryCode(item.getPrimaryCode().toString());
      itemLineageDto.setName(item.getName());
      itemLineageDto.setLineage(item.getLineage().stream().map(ItemLineageDtoFactory::createIdName).collect(Collectors.toList()));
      return itemLineageDto;
   }

   private static IdName createIdName(Item item) {
      return new IdName(item.getId().toString(), item.getName());
   }

   private ItemLineageDtoFactory() {

   }
}
