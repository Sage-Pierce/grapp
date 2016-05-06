package com.wisegas.storemanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDto;
import com.wisegas.common.lang.spacial.GeoPoint;

import java.util.List;

public class ShoppingNodeDto extends NamedDto {

   private String type;
   private GeoPoint location;
   private List<ShoppingItemDto> items;

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public GeoPoint getLocation() {
      return location;
   }

   public void setLocation(GeoPoint location) {
      this.location = location;
   }

   public List<ShoppingItemDto> getItems() {
      return items;
   }

   public void setItems(List<ShoppingItemDto> items) {
      this.items = items;
   }
}
