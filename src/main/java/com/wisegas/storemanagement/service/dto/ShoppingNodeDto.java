package com.wisegas.storemanagement.service.dto;

import java.util.List;

public class ShoppingNodeDto extends AbstractNodeDto {

   private List<ShoppingItemDto> items;

   public List<ShoppingItemDto> getItems() {
      return items;
   }

   public void setItems(List<ShoppingItemDto> items) {
      this.items = items;
   }
}
