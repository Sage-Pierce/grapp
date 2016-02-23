package com.wisegas.grapp.service_impl.factory;

import com.wisegas.grapp.domain.entity.GrappStoreLayoutFeature;
import com.wisegas.grapp.service.dto.GrappStoreLayoutFeatureDTO;

import java.util.ArrayList;
import java.util.List;

public final class GrappStoreLayoutFeatureDTOFactory {

   public static List<GrappStoreLayoutFeatureDTO> createDTOs(Iterable<GrappStoreLayoutFeature> features) {
      List<GrappStoreLayoutFeatureDTO> dtos = new ArrayList<>();
      for (GrappStoreLayoutFeature feature : features) {
         dtos.add(createDTO(feature));
      }
      return dtos;
   }

   public static GrappStoreLayoutFeatureDTO createDTO(GrappStoreLayoutFeature grappStoreLayoutFeature) {
      GrappStoreLayoutFeatureDTO grappStoreLayoutDTO = new GrappStoreLayoutFeatureDTO();
      grappStoreLayoutDTO.setId(grappStoreLayoutFeature.getId().toString());
      grappStoreLayoutDTO.setPolygon(grappStoreLayoutFeature.getPolygon());
      return grappStoreLayoutDTO;
   }

   private GrappStoreLayoutFeatureDTOFactory() {

   }
}
