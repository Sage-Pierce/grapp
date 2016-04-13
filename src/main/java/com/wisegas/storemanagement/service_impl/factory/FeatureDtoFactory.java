package com.wisegas.storemanagement.service_impl.factory;

import com.wisegas.storemanagement.domain.entity.Feature;
import com.wisegas.storemanagement.service.dto.FeatureDto;

public final class FeatureDtoFactory {

   public static FeatureDto createDto(Feature feature) {
      FeatureDto featureDto = new FeatureDto();
      featureDto.setId(feature.getId().toString());
      featureDto.setPolygon(feature.getPolygon());
      return featureDto;
   }

   private FeatureDtoFactory() {

   }
}
