package com.wisegas.stores.service_impl.factory;

import com.wisegas.stores.domain.entity.Node;
import com.wisegas.stores.service.dto.ShoppingItemDto;
import com.wisegas.stores.service.dto.ShoppingNodeDto;

import java.util.List;

public final class ShoppingNodeDtoFactory {

   public static ShoppingNodeDto createDto(Node node, List<ShoppingItemDto> items) {
      ShoppingNodeDto shoppingNodeDto = new ShoppingNodeDto();
      shoppingNodeDto.setId(node.getId().toString());
      shoppingNodeDto.setName(node.getName());
      shoppingNodeDto.setType(node.getType().name());
      shoppingNodeDto.setLocation(node.getLocation());
      shoppingNodeDto.setItems(items);
      return shoppingNodeDto;
   }

   private ShoppingNodeDtoFactory() {

   }
}
