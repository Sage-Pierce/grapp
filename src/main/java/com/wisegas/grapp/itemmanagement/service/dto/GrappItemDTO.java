package com.wisegas.grapp.itemmanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDTO;

import java.util.List;

public class GrappItemDTO extends NamedDTO {

   private String superItemId;
   private List<GrappItemDTO> subItems;

   public String getSuperItemId() {
      return superItemId;
   }

   public void setSuperItemId(String superItemId) {
      this.superItemId = superItemId;
   }

   public List<GrappItemDTO> getSubItems() {
      return subItems;
   }

   public void setSubItems(List<GrappItemDTO> subItems) {
      this.subItems = subItems;
   }
}
