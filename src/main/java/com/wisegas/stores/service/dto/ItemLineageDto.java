package com.wisegas.stores.service.dto;

import com.wisegas.common.lang.value.CodeName;

import java.util.List;

public class ItemLineageDto {

   private CodeName item;
   private List<CodeName> lineage;

   public CodeName getItem() {
      return item;
   }

   public void setItem(CodeName item) {
      this.item = item;
   }

   public List<CodeName> getLineage() {
      return lineage;
   }

   public void setLineage(List<CodeName> lineage) {
      this.lineage = lineage;
   }
}
