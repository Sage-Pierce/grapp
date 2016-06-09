package com.wisegas.stores.service.dto;

import com.wisegas.common.lang.spacial.GeoPolygon;
import com.wisegas.common.lang.value.AbstractDto;

import java.util.List;

public abstract class LayoutDto extends AbstractDto {

   private GeoPolygon outerOutline;
   private GeoPolygon innerOutline;
   private List<FeatureDto> features;

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

   public List<FeatureDto> getFeatures() {
      return features;
   }

   public void setFeatures(List<FeatureDto> features) {
      this.features = features;
   }
}
