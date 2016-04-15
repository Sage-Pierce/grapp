package com.wisegas.storemanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDto;
import com.wisegas.common.lang.value.GeoPoint;

import java.util.List;

public class NodeDto extends NamedDto {

   private String type;
   private GeoPoint location;
   private List<NodeItemDto> items;

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

   public List<NodeItemDto> getItems() {
      return items;
   }

   public void setItems(List<NodeItemDto> items) {
      this.items = items;
   }
}
