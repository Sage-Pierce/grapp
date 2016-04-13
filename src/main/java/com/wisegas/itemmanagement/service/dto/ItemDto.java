package com.wisegas.itemmanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDto;

import java.util.List;

public class ItemDto extends NamedDto {

   private String superItemId;
   private List<ItemDto> subItems;

   public String getSuperItemId() {
      return superItemId;
   }

   public void setSuperItemId(String superItemId) {
      this.superItemId = superItemId;
   }

   public List<ItemDto> getSubItems() {
      return subItems;
   }

   public void setSubItems(List<ItemDto> subItems) {
      this.subItems = subItems;
   }
}
