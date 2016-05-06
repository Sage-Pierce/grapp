package com.wisegas.storemanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDto;
import com.wisegas.common.lang.spacial.GeoPoint;

public class AbstractNodeDto extends NamedDto {

   private String type;
   private GeoPoint location;

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
}
