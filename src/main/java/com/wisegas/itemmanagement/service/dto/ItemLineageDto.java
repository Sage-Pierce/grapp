package com.wisegas.itemmanagement.service.dto;

import com.wisegas.common.lang.value.IdName;

import java.util.List;

public class ItemLineageDto {

   private String primaryCode;
   private String name;
   private List<IdName> lineage;

   public String getPrimaryCode() {
      return primaryCode;
   }

   public void setPrimaryCode(String primaryCode) {
      this.primaryCode = primaryCode;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<IdName> getLineage() {
      return lineage;
   }

   public void setLineage(List<IdName> lineage) {
      this.lineage = lineage;
   }
}
