package com.wisegas.grapp.service.dto;

import com.wisegas.common.lang.dto.BaseDTO;
import com.wisegas.common.lang.value.GeoPolygon;

import java.util.List;

public class GrappStoreLayoutDTO extends BaseDTO {

   private GeoPolygon outerOutline;
   private GeoPolygon innerOutline;
   private List<GrappStoreFeatureDTO> features;
   private List<GrappStoreNodeDTO> nodes;

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

   public List<GrappStoreFeatureDTO> getFeatures() {
      return features;
   }

   public void setFeatures(List<GrappStoreFeatureDTO> features) {
      this.features = features;
   }

   public List<GrappStoreNodeDTO> getNodes() {
      return nodes;
   }

   public void setNodes(List<GrappStoreNodeDTO> nodes) {
      this.nodes = nodes;
   }
}
