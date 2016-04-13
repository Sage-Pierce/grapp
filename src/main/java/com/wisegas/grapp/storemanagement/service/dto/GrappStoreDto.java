package com.wisegas.grapp.storemanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDto;
import com.wisegas.common.lang.value.GeoPoint;

public class GrappStoreDto extends NamedDto {

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
