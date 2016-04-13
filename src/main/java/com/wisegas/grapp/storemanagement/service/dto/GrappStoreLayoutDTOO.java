package com.wisegas.grapp.storemanagement.service.dto;

import com.wisegas.common.lang.dto.BaseDTOO;
import com.wisegas.common.lang.value.GeoPolygon;

import java.util.List;

public class GrappStoreLayoutDTOO extends BaseDTOO {

   private GeoPolygon outerOutline;
   private GeoPolygon innerOutline;
   private List<GrappStoreFeatureDTOO> features;
   private List<GrappStoreNodeDTOO> nodes;

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

   public List<GrappStoreFeatureDTOO> getFeatures() {
      return features;
   }

   public void setFeatures(List<GrappStoreFeatureDTOO> features) {
      this.features = features;
   }

   public List<GrappStoreNodeDTOO> getNodes() {
      return nodes;
   }

   public void setNodes(List<GrappStoreNodeDTOO> nodes) {
      this.nodes = nodes;
   }
}
