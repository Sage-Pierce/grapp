package com.wisegas.stores.service.dto;

import com.wisegas.common.lang.value.AbstractDto;
import com.wisegas.common.lang.value.CodeName;

public class NodeItemDto extends AbstractDto {

   private CodeName item;

   public CodeName getItem() {
      return item;
   }

   public void setItem(CodeName item) {
      this.item = item;
   }
}
