package com.wisegas.storemanagement.service_impl.factory;

import com.wisegas.storemanagement.domain.entity.StoreLayout;
import com.wisegas.storemanagement.service.dto.ShoppingLayoutDto;
import com.wisegas.storemanagement.service.dto.ShoppingNodeDto;

import java.util.List;
import java.util.stream.Collectors;

public final class ShoppingLayoutDtoFactory {

   public static ShoppingLayoutDto createDto(StoreLayout storeLayout, List<ShoppingNodeDto> nodes) {
      ShoppingLayoutDto shoppingLayoutDto = new ShoppingLayoutDto();
      shoppingLayoutDto.setId(storeLayout.getId().toString());
      shoppingLayoutDto.setOuterOutline(storeLayout.getOuterOutline());
      shoppingLayoutDto.setInnerOutline(storeLayout.getInnerOutline());
      shoppingLayoutDto.setFeatures(storeLayout.getFeatures().stream().map(FeatureDtoFactory::createDto).collect(Collectors.toList()));
      shoppingLayoutDto.setNodes(nodes);
      return shoppingLayoutDto;
   }

   private ShoppingLayoutDtoFactory() {

   }
}
