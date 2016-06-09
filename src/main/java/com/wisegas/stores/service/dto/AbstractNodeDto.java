package com.wisegas.stores.service.dto;

import com.wisegas.common.lang.dto.BaseDto;
import com.wisegas.common.lang.spacial.GeoPoint;

public abstract class AbstractNodeDto extends BaseDto {

   private String name;
   private String type;
   private GeoPoint location;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

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
