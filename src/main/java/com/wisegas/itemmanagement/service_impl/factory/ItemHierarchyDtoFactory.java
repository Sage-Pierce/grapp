package com.wisegas.itemmanagement.service_impl.factory;

import com.wisegas.common.lang.value.IdName;
import com.wisegas.itemmanagement.domain.entity.Item;
import com.wisegas.itemmanagement.service.dto.ItemHierarchyDto;

import java.util.stream.Collectors;

public final class ItemHierarchyDtoFactory {

   public static ItemHierarchyDto createDto(Item item) {
      ItemHierarchyDto itemHierarchyDto = new ItemHierarchyDto();
      itemHierarchyDto.setId(item.getId().toString());
      itemHierarchyDto.setName(item.getName());
      itemHierarchyDto.setHierarchy(item.getHierarchy().stream().map(ItemHierarchyDtoFactory::createIdName).collect(Collectors.toList()));
      return itemHierarchyDto;
   }

   private static IdName createIdName(Item item) {
      return new IdName(item.getId().toString(), item.getName());
   }

   private ItemHierarchyDtoFactory() {

   }
}
