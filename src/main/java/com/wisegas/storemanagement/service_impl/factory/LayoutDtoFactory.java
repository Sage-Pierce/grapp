package com.wisegas.storemanagement.service_impl.factory;

import com.wisegas.storemanagement.domain.entity.Layout;
import com.wisegas.storemanagement.service.dto.LayoutDto;

import java.util.stream.Collectors;

public final class LayoutDtoFactory {

   public static LayoutDto createDto(Layout layout) {
      LayoutDto layoutDto = new LayoutDto();
      layoutDto.setId(layout.getId().toString());
      layoutDto.setOuterOutline(layout.getOuterOutline());
      layoutDto.setInnerOutline(layout.getInnerOutline());
      layoutDto.setFeatures(layout.getFeatures().stream().map(FeatureDtoFactory::createDto).collect(Collectors.toList()));
      layoutDto.setNodes(layout.getNodes().stream().map(NodeDtoFactory::createDto).collect(Collectors.toList()));
      return layoutDto;
   }

   private LayoutDtoFactory() {

   }
}
