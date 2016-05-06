package com.wisegas.storemanagement.service_impl.factory;

import com.wisegas.storemanagement.domain.entity.StoreLayout;
import com.wisegas.storemanagement.service.dto.StoreLayoutDto;

import java.util.stream.Collectors;

public final class StoreLayoutDtoFactory {

   public static StoreLayoutDto createDto(StoreLayout storeLayout) {
      StoreLayoutDto storeLayoutDto = new StoreLayoutDto();
      storeLayoutDto.setId(storeLayout.getId().toString());
      storeLayoutDto.setOuterOutline(storeLayout.getOuterOutline());
      storeLayoutDto.setInnerOutline(storeLayout.getInnerOutline());
      storeLayoutDto.setFeatures(storeLayout.getFeatures().stream().map(FeatureDtoFactory::createDto).collect(Collectors.toList()));
      storeLayoutDto.setNodes(storeLayout.getNodes().stream().map(NodeDtoFactory::createDto).collect(Collectors.toList()));
      return storeLayoutDto;
   }

   private StoreLayoutDtoFactory() {

   }
}
