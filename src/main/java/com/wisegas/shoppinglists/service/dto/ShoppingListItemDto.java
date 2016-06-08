package com.wisegas.shoppinglists.service.dto;

import com.wisegas.common.lang.dto.BaseDto;
import com.wisegas.common.lang.value.CodeName;

public class ShoppingListItemDto extends BaseDto {

   private CodeName item;
   private boolean obtained;

   public CodeName getItem() {
      return item;
   }

   public void setItem(CodeName item) {
      this.item = item;
   }

   public boolean isObtained() {
      return obtained;
   }

   public void setObtained(boolean obtained) {
      this.obtained = obtained;
   }
}
