package com.wisegas.shoppinglist.service.dto;

import com.wisegas.common.lang.dto.NamedDto;

import java.util.List;

public class ShoppingListDto extends NamedDto {

   private List<ShoppingListItemDto> items;

   public List<ShoppingListItemDto> getItems() {
      return items;
   }

   public void setItems(List<ShoppingListItemDto> items) {
      this.items = items;
   }
}
