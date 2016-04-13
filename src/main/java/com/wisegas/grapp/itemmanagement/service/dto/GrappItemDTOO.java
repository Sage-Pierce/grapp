package com.wisegas.grapp.itemmanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDTOO;

import java.util.List;

public class GrappItemDTOO extends NamedDTOO {

   private String superItemId;
   private List<GrappItemDTOO> subItems;

   public String getSuperItemId() {
      return superItemId;
   }

   public void setSuperItemId(String superItemId) {
      this.superItemId = superItemId;
   }

   public List<GrappItemDTOO> getSubItems() {
      return subItems;
   }

   public void setSubItems(List<GrappItemDTOO> subItems) {
      this.subItems = subItems;
   }
}
