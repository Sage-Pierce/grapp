package com.wisegas.grapp.service.dto;

import com.wisegas.lang.GeoPoint;
import com.wisegas.lang.NamedDTO;

public class GrappStoreNodeDTO extends NamedDTO {

   private GeoPoint location;

   public GeoPoint getLocation() {
      return location;
   }

   public void setLocation(GeoPoint location) {
      this.location = location;
   }
}
