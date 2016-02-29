package com.wisegas.grapp.service.dto;

import com.wisegas.lang.BaseDTO;
import com.wisegas.lang.GeoPolygon;

public class GrappStoreFeatureDTO extends BaseDTO {

   private GeoPolygon polygon;

   public GeoPolygon getPolygon() {
      return polygon;
   }

   public void setPolygon(GeoPolygon polygon) {
      this.polygon = polygon;
   }
}
