package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreFeature;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreFeatureDto;

public final class GrappStoreFeatureDtoFactory {

   public static GrappStoreFeatureDto createDTO(GrappStoreFeature grappStoreFeature) {
      GrappStoreFeatureDto grappStoreLayoutDTO = new GrappStoreFeatureDto();
      grappStoreLayoutDTO.setId(grappStoreFeature.getId().toString());
      grappStoreLayoutDTO.setPolygon(grappStoreFeature.getPolygon());
      return grappStoreLayoutDTO;
   }

   private GrappStoreFeatureDtoFactory() {

   }
}
