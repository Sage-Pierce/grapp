package org.codegas.stores.service_impl.api;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.annotation.ApplicationService;
import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.stores.domain.repository.FeatureRepository;
import org.codegas.stores.domain.value.FeatureId;
import org.codegas.stores.service.api.FeatureService;
import org.codegas.stores.service.dto.FeatureDto;
import org.codegas.stores.service_impl.factory.FeatureDtoFactory;

@Named
@Singleton
@Transactional
@ApplicationService
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    @Inject
    public FeatureServiceImpl(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @Override
    public FeatureDto get(String id) {
        return FeatureDtoFactory.createDto(featureRepository.get(FeatureId.fromString(id)));
    }

    @Override
    public void delete(String id) {
        featureRepository.remove(FeatureId.fromString(id));
    }
}
