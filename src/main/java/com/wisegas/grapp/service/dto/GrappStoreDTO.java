package com.wisegas.grapp.service.dto;

import com.wisegas.lang.GeoPoint;
import com.wisegas.lang.NamedDTO;

public class GrappStoreDTO extends NamedDTO {

   private GeoPoint location;

   private String layoutID;

   public GeoPoint getLocation() {
      return location;
   }

   public void setLocation(GeoPoint location) {
      this.location = location;
   }

   public String getLayoutID() {
      return layoutID;
   }

   public void setLayoutID(String layoutID) {
      this.layoutID = layoutID;
   }
}
