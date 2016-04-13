package com.wisegas.grapp.itemmanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDto;

import java.util.List;

public class GrappItemDto extends NamedDto {

   private String superItemId;
   private List<GrappItemDto> subItems;

   public String getSuperItemId() {
      return superItemId;
   }

   public void setSuperItemId(String superItemId) {
      this.superItemId = superItemId;
   }

   public List<GrappItemDto> getSubItems() {
      return subItems;
   }

   public void setSubItems(List<GrappItemDto> subItems) {
      this.subItems = subItems;
   }
}
