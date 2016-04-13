package com.wisegas.grapp.storemanagement.service.dto;

import com.wisegas.common.lang.dto.BaseDTOO;
import com.wisegas.common.lang.value.GeoPolygon;

public class GrappStoreFeatureDTOO extends BaseDTOO {

   private GeoPolygon polygon;

   public GeoPolygon getPolygon() {
      return polygon;
   }

   public void setPolygon(GeoPolygon polygon) {
      this.polygon = polygon;
   }
}
