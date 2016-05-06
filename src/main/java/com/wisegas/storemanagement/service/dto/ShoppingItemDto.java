package com.wisegas.storemanagement.service.dto;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.storemanagement.service.value.ShoppingItemType;

public class ShoppingItemDto {

   private CodeName item;
   private String type;

   public CodeName getItem() {
      return item;
   }

   public void setItem(CodeName item) {
      this.item = item;
   }

   public String getType() {
      return type;
   }

   public void setType(ShoppingItemType type) {
      this.type = type.name();
   }
}
