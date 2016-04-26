package com.wisegas.storemanagement.service.dto;

import com.wisegas.common.lang.dto.BaseDto;
import com.wisegas.common.lang.spacial.GeoPolygon;

import java.util.List;

public class LayoutDto extends BaseDto {

   private GeoPolygon outerOutline;
   private GeoPolygon innerOutline;
   private List<FeatureDto> features;
   private List<NodeDto> nodes;

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

   public List<NodeDto> getNodes() {
      return nodes;
   }

   public void setNodes(List<NodeDto> nodes) {
      this.nodes = nodes;
   }
}
