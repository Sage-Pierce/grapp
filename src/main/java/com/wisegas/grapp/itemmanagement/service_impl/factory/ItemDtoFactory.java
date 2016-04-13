package com.wisegas.grapp.itemmanagement.service_impl.factory;

import com.wisegas.grapp.itemmanagement.domain.entity.Item;
import com.wisegas.grapp.itemmanagement.service.dto.ItemDto;

import java.util.stream.Collectors;

public final class ItemDtoFactory {

   public static ItemDto createDto(Item item) {
      ItemDto itemDto = new ItemDto();
      itemDto.setId(item.getId().toString());
      itemDto.setSuperItemId(item.isGeneralItem() ? null : item.getSuperItem().getName());
      itemDto.setName(item.getName());
      itemDto.setSubItems(item.getSubItems().stream().map(ItemDtoFactory::createDto).collect(Collectors.toList()));
      return itemDto;
   }

   private ItemDtoFactory() {

   }
}
