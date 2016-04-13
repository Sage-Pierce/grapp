package com.wisegas.grapp.storemanagement.service_impl.factory;

import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreLayoutDto;

import java.util.stream.Collectors;

public final class GrappStoreLayoutDtoFactory {

   public static GrappStoreLayoutDto createDto(GrappStoreLayout grappStoreLayout) {
      GrappStoreLayoutDto grappStoreLayoutDto = new GrappStoreLayoutDto();
      grappStoreLayoutDto.setId(grappStoreLayout.getId().toString());
      grappStoreLayoutDto.setOuterOutline(grappStoreLayout.getOuterOutline());
      grappStoreLayoutDto.setInnerOutline(grappStoreLayout.getInnerOutline());
      grappStoreLayoutDto.setFeatures(grappStoreLayout.getFeatures().stream().map(GrappStoreFeatureDtoFactory::createDto).collect(Collectors.toList()));
      grappStoreLayoutDto.setNodes(grappStoreLayout.getNodes().stream().map(GrappStoreNodeDtoFactory::createDto).collect(Collectors.toList()));
      return grappStoreLayoutDto;
   }

   private GrappStoreLayoutDtoFactory() {

   }
}
