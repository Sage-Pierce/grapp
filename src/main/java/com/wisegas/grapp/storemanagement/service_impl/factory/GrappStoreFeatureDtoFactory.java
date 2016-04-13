package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreFeature;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreFeatureDto;

public final class GrappStoreFeatureDtoFactory {

   public static GrappStoreFeatureDto createDto(GrappStoreFeature grappStoreFeature) {
      GrappStoreFeatureDto grappStoreLayoutDto = new GrappStoreFeatureDto();
      grappStoreLayoutDto.setId(grappStoreFeature.getId().toString());
      grappStoreLayoutDto.setPolygon(grappStoreFeature.getPolygon());
      return grappStoreLayoutDto;
   }

   private GrappStoreFeatureDtoFactory() {

   }
}
