package com.wisegas.stores.service_impl.factory;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.stores.service.dto.ShoppingItemDto;
import com.wisegas.stores.service.value.ShoppingItemType;

public final class ShoppingItemDtoFactory {

   public static ShoppingItemDto createDto(CodeName item, ShoppingItemType type) {
      ShoppingItemDto shoppingItemDto = new ShoppingItemDto();
      shoppingItemDto.setItem(item);
      shoppingItemDto.setType(type);
      return shoppingItemDto;
   }

   private ShoppingItemDtoFactory() {

   }
}
