package com.wisegas.grapp.service_impl.factory;

import com.wisegas.grapp.domain.entity.GrappStoreLayout;
import com.wisegas.grapp.service.dto.GrappStoreLayoutDTO;

import java.util.stream.Collectors;

public final class GrappStoreLayoutDTOFactory {

   public static GrappStoreLayoutDTO createDTO(GrappStoreLayout grappStoreLayout) {
      GrappStoreLayoutDTO grappStoreLayoutDTO = new GrappStoreLayoutDTO();
      grappStoreLayoutDTO.setId(grappStoreLayout.getId().toString());
      grappStoreLayoutDTO.setOuterOutline(grappStoreLayout.getOuterOutline());
      grappStoreLayoutDTO.setInnerOutline(grappStoreLayout.getInnerOutline());
      grappStoreLayoutDTO.setFeatures(grappStoreLayout.getFeatures().stream().map(GrappStoreFeatureDTOFactory::createDTO).collect(Collectors.toList()));
      grappStoreLayoutDTO.setNodes(grappStoreLayout.getNodes().stream().map(GrappStoreNodeDTOFactory::createDTO).collect(Collectors.toList()));
      return grappStoreLayoutDTO;
   }

   private GrappStoreLayoutDTOFactory() {

   }
}
