package com.wisegas.itemmanagement.service.dto;

import com.wisegas.common.lang.value.CodeName;

import java.util.List;

public class ItemDto {

   private String primaryCode;
   private String name;
   private String superItemCode;
   private List<CodeName> lineage;
   private List<ItemDto> subItems;

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

   public String getSuperItemCode() {
      return superItemCode;
   }

   public void setSuperItemCode(String superItemCode) {
      this.superItemCode = superItemCode;
   }

   public List<CodeName> getLineage() {
      return lineage;
   }

   public void setLineage(List<CodeName> lineage) {
      this.lineage = lineage;
   }

   public List<ItemDto> getSubItems() {
      return subItems;
   }

   public void setSubItems(List<ItemDto> subItems) {
      this.subItems = subItems;
   }
}
