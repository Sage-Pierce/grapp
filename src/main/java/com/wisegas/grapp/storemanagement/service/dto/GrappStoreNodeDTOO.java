package com.wisegas.grapp.storemanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDTOO;
import com.wisegas.common.lang.value.GeoPoint;

public class GrappStoreNodeDTOO extends NamedDTOO {

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
