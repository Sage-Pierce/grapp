package com.wisegas.grapp.service.dto;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.common.lang.dto.NamedDTO;

public class GrappStoreNodeDTO extends NamedDTO {

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
