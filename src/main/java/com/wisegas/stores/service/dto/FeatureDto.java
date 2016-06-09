package com.wisegas.stores.service.dto;

import com.wisegas.common.lang.spacial.GeoPolygon;
import com.wisegas.common.lang.value.BaseDto;

public class FeatureDto extends BaseDto {

   private GeoPolygon polygon;

   public GeoPolygon getPolygon() {
      return polygon;
   }

   public void setPolygon(GeoPolygon polygon) {
      this.polygon = polygon;
   }
}
