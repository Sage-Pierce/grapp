package com.wisegas.grapp.storemanagement.service_impl.api_impl;

import com.wisegas.common.lang.annotation.ApplicationService;
import com.wisegas.common.lang.annotation.Transactional;
import com.wisegas.grapp.storemanagement.domain.repository.GrappStoreFeatureRepository;
import com.wisegas.grapp.storemanagement.domain.value.GrappStoreFeatureId;
import com.wisegas.grapp.storemanagement.service.api.GrappStoreFeatureService;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreFeatureDTO;
import com.wisegas.grapp.storemanagement.service_impl.factory.GrappStoreFeatureDTOFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
@ApplicationService
public class GrappStoreFeatureServiceImpl implements GrappStoreFeatureService {

   private final GrappStoreFeatureRepository grappStoreFeatureRepository;

   @Inject
   public GrappStoreFeatureServiceImpl(GrappStoreFeatureRepository grappStoreFeatureRepository) {
      this.grappStoreFeatureRepository = grappStoreFeatureRepository;
   }

   @Override
   public GrappStoreFeatureDTO get(String id) {
      return GrappStoreFeatureDTOFactory.createDTO(grappStoreFeatureRepository.get(GrappStoreFeatureId.fromString(id)));
   }
}
