package com.wisegas.grapp.itemmanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDTO;

import java.util.List;

public class GrappItemDTO extends NamedDTO {

   private String superItemName;
   private List<GrappItemDTO> subItems;

   public String getSuperItemName() {
      return superItemName;
   }

   public void setSuperItemName(String superItemName) {
      this.superItemName = superItemName;
   }

   public List<GrappItemDTO> getSubItems() {
      return subItems;
   }

   public void setSubItems(List<GrappItemDTO> subItems) {
      this.subItems = subItems;
   }
}
