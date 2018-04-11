package org.codegas.shoppinglists.service_impl.factory;

import java.util.stream.Collectors;

import org.codegas.commons.lang.value.IdName;
import org.codegas.shoppinglists.domain.entity.Shopper;
import org.codegas.shoppinglists.service.dto.ShopperDto;

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
