package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreFeature;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreFeatureDTOO;

public final class GrappStoreFeatureDTOFactory {

   public static GrappStoreFeatureDTOO createDTO(GrappStoreFeature grappStoreFeature) {
      GrappStoreFeatureDTOO grappStoreLayoutDTO = new GrappStoreFeatureDTOO();
      grappStoreLayoutDTO.setId(grappStoreFeature.getId().toString());
      grappStoreLayoutDTO.setPolygon(grappStoreFeature.getPolygon());
      return grappStoreLayoutDTO;
   }

   private GrappStoreFeatureDTOFactory() {

   }
}
