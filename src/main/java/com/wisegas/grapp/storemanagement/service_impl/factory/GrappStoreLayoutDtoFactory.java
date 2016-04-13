package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreLayoutDto;

import java.util.stream.Collectors;

public final class GrappStoreLayoutDtoFactory {

   public static GrappStoreLayoutDto createDto(GrappStoreLayout grappStoreLayout) {
      GrappStoreLayoutDto grappStoreLayoutDTO = new GrappStoreLayoutDto();
      grappStoreLayoutDTO.setId(grappStoreLayout.getId().toString());
      grappStoreLayoutDTO.setOuterOutline(grappStoreLayout.getOuterOutline());
      grappStoreLayoutDTO.setInnerOutline(grappStoreLayout.getInnerOutline());
      grappStoreLayoutDTO.setFeatures(grappStoreLayout.getFeatures().stream().map(GrappStoreFeatureDtoFactory::createDto).collect(Collectors.toList()));
      grappStoreLayoutDTO.setNodes(grappStoreLayout.getNodes().stream().map(GrappStoreNodeDtoFactory::createDto).collect(Collectors.toList()));
      return grappStoreLayoutDTO;
   }

   private GrappStoreLayoutDtoFactory() {

   }
}
