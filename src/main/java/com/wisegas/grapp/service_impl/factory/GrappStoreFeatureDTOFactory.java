package com.wisegas.grapp.service_impl.factory;

import com.wisegas.grapp.domain.entity.GrappStoreFeature;
import com.wisegas.grapp.service.dto.GrappStoreFeatureDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class GrappStoreFeatureDTOFactory {

   public static List<GrappStoreFeatureDTO> createDTOs(Collection<GrappStoreFeature> features) {
      return features.stream().map(GrappStoreFeatureDTOFactory::createDTO).collect(Collectors.toList());
   }

   public static GrappStoreFeatureDTO createDTO(GrappStoreFeature grappStoreFeature) {
      GrappStoreFeatureDTO grappStoreLayoutDTO = new GrappStoreFeatureDTO();
      grappStoreLayoutDTO.setId(grappStoreFeature.getId().toString());
      grappStoreLayoutDTO.setPolygon(grappStoreFeature.getPolygon());
      return grappStoreLayoutDTO;
   }

   private GrappStoreFeatureDTOFactory() {

   }
}
