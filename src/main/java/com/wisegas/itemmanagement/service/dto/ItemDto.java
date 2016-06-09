package com.wisegas.itemmanagement.service.dto;

import java.util.List;

public class ItemDto extends ItemLineageDto {

   private String superItemCode;
   private List<ItemDto> subItems;

   public String getSuperItemCode() {
      return superItemCode;
   }

   public void setSuperItemCode(String superItemCode) {
      this.superItemCode = superItemCode;
   }

   public List<ItemDto> getSubItems() {
      return subItems;
   }

   public void setSubItems(List<ItemDto> subItems) {
      this.subItems = subItems;
   }
}
