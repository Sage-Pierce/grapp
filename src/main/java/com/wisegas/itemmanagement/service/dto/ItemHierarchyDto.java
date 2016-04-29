package com.wisegas.itemmanagement.service.dto;

import com.wisegas.common.lang.value.IdName;

import java.util.List;

public class ItemHierarchyDto {

   private String primaryCode;
   private String name;
   private List<IdName> hierarchy;

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

   public List<IdName> getHierarchy() {
      return hierarchy;
   }

   public void setHierarchy(List<IdName> hierarchy) {
      this.hierarchy = hierarchy;
   }
}
