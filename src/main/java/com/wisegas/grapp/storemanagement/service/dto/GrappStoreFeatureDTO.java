package com.wisegas.grapp.storemanagement.service.dto;

import com.wisegas.common.lang.dto.BaseDTO;
import com.wisegas.common.lang.value.GeoPolygon;

public class GrappStoreFeatureDTO extends BaseDTO {

   private GeoPolygon polygon;

   public GeoPolygon getPolygon() {
      return polygon;
   }

   public void setPolygon(GeoPolygon polygon) {
      this.polygon = polygon;
   }
}
