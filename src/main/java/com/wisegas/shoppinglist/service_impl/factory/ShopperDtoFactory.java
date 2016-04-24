package com.wisegas.shoppinglist.service_impl.factory;

import com.wisegas.common.lang.value.IdName;
import com.wisegas.shoppinglist.domain.entity.Shopper;
import com.wisegas.shoppinglist.service.dto.ShopperDto;

import java.util.stream.Collectors;

public final class ShopperDtoFactory {

   public static ShopperDto createDto(Shopper shopper) {
      ShopperDto shopperDto = new ShopperDto();
      shopperDto.setId(shopper.getId().toString());
      shopperDto.setLists(shopper.getLists().stream().map(list -> new IdName(list.getId().toString(), list.getName())).collect(Collectors.toList()));
      return shopperDto;
   }

   private ShopperDtoFactory() {

   }
}
