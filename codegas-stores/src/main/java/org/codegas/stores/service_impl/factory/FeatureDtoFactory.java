package org.codegas.stores.service_impl.factory;

import org.codegas.stores.domain.entity.Feature;
import org.codegas.stores.service.dto.FeatureDto;

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
