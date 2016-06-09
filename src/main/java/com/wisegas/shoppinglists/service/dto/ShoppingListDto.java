package com.wisegas.shoppinglists.service.dto;

import com.wisegas.common.lang.dto.BaseDto;

import java.util.List;

public class ShoppingListDto extends BaseDto {

   private String name;
   private List<ShoppingListItemDto> items;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<ShoppingListItemDto> getItems() {
      return items;
   }

   public void setItems(List<ShoppingListItemDto> items) {
      this.items = items;
   }
}
