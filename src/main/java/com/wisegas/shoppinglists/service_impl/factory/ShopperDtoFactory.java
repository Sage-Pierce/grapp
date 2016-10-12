package com.wisegas.shoppinglists.service_impl.factory;

import com.wisegas.common.lang.value.IdName;
import com.wisegas.shoppinglists.domain.entity.Shopper;
import com.wisegas.shoppinglists.service.dto.ShopperDto;

import java.util.stream.Collectors;

public final class ShopperDtoFactory {

   private ShopperDtoFactory() {

   }

   public static ShopperDto createDto(Shopper shopper) {
      ShopperDto shopperDto = new ShopperDto();
      shopperDto.setEmail(shopper.getEmail().toString());
      shopperDto.setLists(shopper.getLists().stream().map(list -> new IdName(list.getId().toString(), list.getName())).collect(Collectors.toList()));
      return shopperDto;
   }
}
