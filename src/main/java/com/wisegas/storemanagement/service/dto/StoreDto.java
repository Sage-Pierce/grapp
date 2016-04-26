package com.wisegas.storemanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDto;
import com.wisegas.common.lang.spacial.GeoPoint;

public class StoreDto extends NamedDto {

   private GeoPoint location;
   private String layoutId;

   public GeoPoint getLocation() {
      return location;
   }

   public void setLocation(GeoPoint location) {
      this.location = location;
   }

   public String getLayoutId() {
      return layoutId;
   }

   public void setLayoutId(String layoutId) {
      this.layoutId = layoutId;
   }
}
