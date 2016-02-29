package com.wisegas.grapp.service_impl.api;

import com.wisegas.grapp.domain.repository.GrappStoreFeatureRepository;
import com.wisegas.grapp.domain.value.GrappStoreFeatureID;
import com.wisegas.grapp.service.api.GrappStoreFeatureService;
import com.wisegas.grapp.service.dto.GrappStoreFeatureDTO;
import com.wisegas.grapp.service_impl.factory.GrappStoreFeatureDTOFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Transactional
public class GrappStoreFeatureServiceImpl implements GrappStoreFeatureService {

   private final GrappStoreFeatureRepository grappStoreFeatureRepository;

   @Inject
   public GrappStoreFeatureServiceImpl(GrappStoreFeatureRepository grappStoreFeatureRepository) {
      this.grappStoreFeatureRepository = grappStoreFeatureRepository;
   }

   @Override
   public GrappStoreFeatureDTO findByID(String id) {
      return GrappStoreFeatureDTOFactory.createDTO(grappStoreFeatureRepository.findByID(GrappStoreFeatureID.fromString(id)));
   }
}
