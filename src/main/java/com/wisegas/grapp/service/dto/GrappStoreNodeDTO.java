package com.wisegas.grapp.service.dto;

import com.wisegas.lang.GeoPoint;
import com.wisegas.lang.NamedDTO;

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
