package com.wisegas.grapp.storemanagement.service.dto;

import com.wisegas.common.lang.dto.BaseDTOO;
import com.wisegas.common.lang.value.CodeName;

public class GrappStoreNodeItemDTOO extends BaseDTOO {

   private CodeName item;

   public CodeName getItem() {
      return item;
   }

   public void setItem(CodeName item) {
      this.item = item;
   }
}
