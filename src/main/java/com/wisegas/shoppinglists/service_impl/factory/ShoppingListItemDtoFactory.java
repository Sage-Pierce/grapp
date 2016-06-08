package com.wisegas.shoppinglists.service_impl.factory;

import com.wisegas.shoppinglists.domain.entity.ShoppingListItem;
import com.wisegas.shoppinglists.service.dto.ShoppingListItemDto;

public final class ShoppingListItemDtoFactory {

   public static ShoppingListItemDto createDto(ShoppingListItem shoppingListItem) {
      ShoppingListItemDto shoppingListItemDto = new ShoppingListItemDto();
      shoppingListItemDto.setId(shoppingListItem.getId().toString());
      shoppingListItemDto.setItem(shoppingListItem.getItem().toCodeName());
      shoppingListItemDto.setObtained(shoppingListItem.isObtained());
      return shoppingListItemDto;
   }

   private ShoppingListItemDtoFactory() {

   }
}
