package com.wisegas.itemmanagement.service_impl.factory;

import com.wisegas.itemmanagement.domain.entity.Item;
import com.wisegas.itemmanagement.service.dto.ItemDto;

import java.util.stream.Collectors;

public final class ItemDtoFactory {

   public static ItemDto createDto(Item item) {
      ItemDto itemDto = new ItemDto();
      itemDto.setPrimaryCode(item.getPrimaryCode().toString());
      itemDto.setSuperItemCode(item.isGeneralItem() ? null : item.getSuperItem().getPrimaryCode().toString());
      itemDto.setName(item.getName());
      itemDto.setSubItems(item.getSubItems().stream().map(ItemDtoFactory::createDto).collect(Collectors.toList()));
      return itemDto;
   }

   private ItemDtoFactory() {

   }
}
