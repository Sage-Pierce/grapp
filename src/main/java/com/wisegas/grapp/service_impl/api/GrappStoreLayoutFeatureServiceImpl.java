package com.wisegas.grapp.service_impl.api;

import com.wisegas.grapp.domain.repository.GrappStoreLayoutFeatureRepository;
import com.wisegas.grapp.domain.value.GrappStoreLayoutFeatureID;
import com.wisegas.grapp.service.api.GrappStoreLayoutFeatureService;
import com.wisegas.grapp.service.dto.GrappStoreLayoutFeatureDTO;
import com.wisegas.grapp.service_impl.factory.GrappStoreLayoutFeatureDTOFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
public class GrappStoreLayoutFeatureServiceImpl implements GrappStoreLayoutFeatureService {

   private final GrappStoreLayoutFeatureRepository grappStoreLayoutFeatureRepository;

   @Inject
   public GrappStoreLayoutFeatureServiceImpl(GrappStoreLayoutFeatureRepository grappStoreLayoutFeatureRepository) {
      this.grappStoreLayoutFeatureRepository = grappStoreLayoutFeatureRepository;
   }

   @Override
   public GrappStoreLayoutFeatureDTO findByID(String id) {
      return GrappStoreLayoutFeatureDTOFactory.createDTO(grappStoreLayoutFeatureRepository.findByID(GrappStoreLayoutFeatureID.fromString(id)));
   }
}
