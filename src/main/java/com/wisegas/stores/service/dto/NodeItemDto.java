package com.wisegas.stores.service.dto;

import com.wisegas.common.lang.dto.BaseDto;
import com.wisegas.common.lang.value.CodeName;

public class NodeItemDto extends BaseDto {

   private CodeName item;

   public CodeName getItem() {
      return item;
   }

   public void setItem(CodeName item) {
      this.item = item;
   }
}
