package com.wisegas.grapp.storemanagement.service.dto;

import com.wisegas.common.lang.dto.BaseDto;
import com.wisegas.common.lang.value.GeoPolygon;

import java.util.List;

public class GrappStoreLayoutDto extends BaseDto {

   private GeoPolygon outerOutline;
   private GeoPolygon innerOutline;
   private List<GrappStoreFeatureDto> features;
   private List<GrappStoreNodeDto> nodes;

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

   public List<GrappStoreFeatureDto> getFeatures() {
      return features;
   }

   public void setFeatures(List<GrappStoreFeatureDto> features) {
      this.features = features;
   }

   public List<GrappStoreNodeDto> getNodes() {
      return nodes;
   }

   public void setNodes(List<GrappStoreNodeDto> nodes) {
      this.nodes = nodes;
   }
}
