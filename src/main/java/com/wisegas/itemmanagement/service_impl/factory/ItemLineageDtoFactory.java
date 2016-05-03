package com.wisegas.itemmanagement.service_impl.factory;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.itemmanagement.domain.entity.Item;
import com.wisegas.itemmanagement.service.dto.ItemLineageDto;

import java.util.stream.Collectors;

public final class ItemLineageDtoFactory {

   public static ItemLineageDto createDto(Item item) {
      ItemLineageDto itemLineageDto = new ItemLineageDto();
      itemLineageDto.setPrimaryCode(item.getPrimaryCode().toString());
      itemLineageDto.setName(item.getName());
      itemLineageDto.setLineage(item.getLineage().stream().map(ItemLineageDtoFactory::createCodeName).collect(Collectors.toList()));
      return itemLineageDto;
   }

   private static CodeName createCodeName(Item item) {
      return new CodeName(item.getPrimaryCode().toString(), item.getName());
   }

   private ItemLineageDtoFactory() {

   }
}
