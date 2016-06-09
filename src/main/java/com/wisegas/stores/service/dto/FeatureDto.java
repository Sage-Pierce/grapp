package com.wisegas.stores.service.dto;

import com.wisegas.common.lang.spacial.GeoPolygon;
import com.wisegas.common.lang.value.AbstractDto;

public class FeatureDto extends AbstractDto {

   private GeoPolygon polygon;

   public GeoPolygon getPolygon() {
      return polygon;
   }

   public void setPolygon(GeoPolygon polygon) {
      this.polygon = polygon;
   }
}
