package com.wisegas.itemmanagement.service_impl.factory;

import com.wisegas.itemmanagement.domain.entity.Item;
import com.wisegas.itemmanagement.service.dto.ItemLineageDto;

import java.util.stream.Collectors;

public final class ItemLineageDtoFactory {

   private ItemLineageDtoFactory() {

   }

   public static ItemLineageDto createDto(Item item) {
      ItemLineageDto itemLineageDto = new ItemLineageDto();
      itemLineageDto.setPrimaryCode(item.getPrimaryCode().toString());
      itemLineageDto.setName(item.getName());
      itemLineageDto.setLineage(item.getLineage().stream().map(Item::toCodeName).collect(Collectors.toList()));
      return itemLineageDto;
   }
}
