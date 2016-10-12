package com.wisegas.stores.service_impl.factory;

import com.wisegas.stores.domain.entity.Feature;
import com.wisegas.stores.service.dto.FeatureDto;

public final class FeatureDtoFactory {

   private FeatureDtoFactory() {

   }

   public static FeatureDto createDto(Feature feature) {
      FeatureDto featureDto = new FeatureDto();
      featureDto.setId(feature.getId().toString());
      featureDto.setPolygon(feature.getPolygon());
      return featureDto;
   }
}
