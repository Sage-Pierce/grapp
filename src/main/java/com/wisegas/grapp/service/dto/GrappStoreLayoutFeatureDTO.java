package com.wisegas.grapp.service.dto;

import com.wisegas.value.BaseDTO;
import com.wisegas.value.GeoPolygon;

public class GrappStoreLayoutFeatureDTO extends BaseDTO {

   private GeoPolygon polygon;

   public GeoPolygon getPolygon() {
      return polygon;
   }

   public void setPolygon(GeoPolygon polygon) {
      this.polygon = polygon;
   }
}
