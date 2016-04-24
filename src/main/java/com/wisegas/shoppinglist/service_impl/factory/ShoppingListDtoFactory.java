package com.wisegas.shoppinglist.service_impl.factory;

import com.wisegas.shoppinglist.domain.entity.ShoppingList;
import com.wisegas.shoppinglist.service.dto.ShoppingListDto;

import java.util.stream.Collectors;

public final class ShoppingListDtoFactory {

   public static ShoppingListDto createDto(ShoppingList shoppingList) {
      ShoppingListDto shoppingListDto = new ShoppingListDto();
      shoppingListDto.setId(shoppingList.getId().toString());
      shoppingListDto.setName(shoppingList.getName());
      shoppingListDto.setItems(shoppingList.getItems().stream().map(ShoppingListItemDtoFactory::createDto).collect(Collectors.toList()));
      return shoppingListDto;
   }

   private ShoppingListDtoFactory() {

   }
}
