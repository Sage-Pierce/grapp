package com.wisegas.storemanagement.service.dto;

import com.wisegas.common.lang.dto.BaseDto;
import com.wisegas.common.lang.spacial.GeoPolygon;

public class FeatureDto extends BaseDto {

   private GeoPolygon polygon;

   public GeoPolygon getPolygon() {
      return polygon;
   }

   public void setPolygon(GeoPolygon polygon) {
      this.polygon = polygon;
   }
}
