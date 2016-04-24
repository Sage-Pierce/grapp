package com.wisegas.shoppinglist.service_impl.factory;

import com.wisegas.shoppinglist.domain.entity.ShoppingListItem;
import com.wisegas.shoppinglist.service.dto.ShoppingListItemDto;

public final class ShoppingListItemDtoFactory {

   public static ShoppingListItemDto createDto(ShoppingListItem shoppingListItem) {
      ShoppingListItemDto shoppingListItemDto = new ShoppingListItemDto();
      shoppingListItemDto.setId(shoppingListItem.getId().toString());
      shoppingListItemDto.setItem(shoppingListItem.getItem().toCodeName());
      return shoppingListItemDto;
   }

   private ShoppingListItemDtoFactory() {

   }
}
