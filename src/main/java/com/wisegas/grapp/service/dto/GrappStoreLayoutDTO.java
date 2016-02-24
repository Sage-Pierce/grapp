package com.wisegas.grapp.service.dto;

import com.wisegas.lang.BaseDTO;
import com.wisegas.lang.GeoPolygon;

import java.util.List;

public class GrappStoreLayoutDTO extends BaseDTO {

   private GeoPolygon outerOutline;
   private GeoPolygon innerOutline;
   private List<GrappStoreLayoutFeatureDTO> features;

   public GeoPolygon getOuterOutline() {
      return outerOutline;
   }

   public void setOuterOutline(GeoPolygon outerOutline) {
      this.outerOutline = outerOutline;
   }


   public GeoPolygon getInnerOutline() {
      return innerOutline;
   }

   public void setInnerOutline(GeoPolygon innerOutline) {
      this.innerOutline = innerOutline;
   }

   public List<GrappStoreLayoutFeatureDTO> getFeatures() {
      return features;
   }

   public void setFeatures(List<GrappStoreLayoutFeatureDTO> features) {
      this.features = features;
   }
}
