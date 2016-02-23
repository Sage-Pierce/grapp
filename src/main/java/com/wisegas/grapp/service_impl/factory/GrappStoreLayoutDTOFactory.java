package com.wisegas.grapp.service_impl.factory;

import com.wisegas.grapp.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.service.dto.GrappStoreLayoutDTO;

public final class GrappStoreLayoutDTOFactory {

   public static GrappStoreLayoutDTO createDTO(GrappStoreLayout grappStoreLayout) {
      GrappStoreLayoutDTO grappStoreLayoutDTO = new GrappStoreLayoutDTO();
      grappStoreLayoutDTO.setId(grappStoreLayout.getId().toString());
      grappStoreLayoutDTO.setOuterOutline(grappStoreLayout.getOuterOutline());
      grappStoreLayoutDTO.setInnerOutline(grappStoreLayout.getInnerOutline());
      grappStoreLayoutDTO.setFeatures(GrappStoreLayoutFeatureDTOFactory.createDTOs(grappStoreLayout.getFeatures()));
      return grappStoreLayoutDTO;
   }

   private GrappStoreLayoutDTOFactory() {

   }
}
