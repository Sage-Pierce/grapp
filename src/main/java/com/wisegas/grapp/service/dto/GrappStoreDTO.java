package com.wisegas.grapp.service.dto;

import com.wisegas.common.lang.dto.NamedDTO;
import com.wisegas.common.lang.value.GeoPoint;

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
