package com.wisegas.stores.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.stores.domain.repository.FeatureRepository;
import com.wisegas.stores.domain.value.FeatureId;
import com.wisegas.stores.service.api.FeatureService;
import com.wisegas.stores.service.dto.FeatureDto;
import com.wisegas.stores.service_impl.factory.FeatureDtoFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

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
