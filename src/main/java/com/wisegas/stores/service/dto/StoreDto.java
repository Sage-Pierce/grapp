package com.wisegas.stores.service.dto;

import com.wisegas.common.lang.dto.BaseDto;
import com.wisegas.common.lang.spacial.GeoPoint;

public class StoreDto extends BaseDto {

   private String name;
   private GeoPoint location;
   private String layoutId;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

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
