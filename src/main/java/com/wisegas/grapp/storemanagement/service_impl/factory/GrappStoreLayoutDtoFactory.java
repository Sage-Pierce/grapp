package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreLayoutDTOO;

import java.util.stream.Collectors;

public final class GrappStoreLayoutDtoFactory {

   public static GrappStoreLayoutDTOO createDTO(GrappStoreLayout grappStoreLayout) {
      GrappStoreLayoutDTOO grappStoreLayoutDTO = new GrappStoreLayoutDTOO();
      grappStoreLayoutDTO.setId(grappStoreLayout.getId().toString());
      grappStoreLayoutDTO.setOuterOutline(grappStoreLayout.getOuterOutline());
      grappStoreLayoutDTO.setInnerOutline(grappStoreLayout.getInnerOutline());
      grappStoreLayoutDTO.setFeatures(grappStoreLayout.getFeatures().stream().map(GrappStoreFeatureDtoFactory::createDTO).collect(Collectors.toList()));
      grappStoreLayoutDTO.setNodes(grappStoreLayout.getNodes().stream().map(GrappStoreNodeDtoFactory::createDTO).collect(Collectors.toList()));
      return grappStoreLayoutDTO;
   }

   private GrappStoreLayoutDtoFactory() {

   }
}
