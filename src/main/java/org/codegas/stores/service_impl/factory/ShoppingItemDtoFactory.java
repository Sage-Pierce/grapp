package org.codegas.stores.service_impl.factory;

import org.codegas.commons.lang.value.CodeName;
import org.codegas.stores.service.dto.ShoppingItemDto;
import org.codegas.stores.service.value.ShoppingItemType;

public final class ShoppingItemDtoFactory {

    private ShoppingItemDtoFactory() {

    }

    public static ShoppingItemDto createDto(CodeName item, ShoppingItemType type) {
        ShoppingItemDto shoppingItemDto = new ShoppingItemDto();
        shoppingItemDto.setItem(item);
        shoppingItemDto.setType(type);
        return shoppingItemDto;
    }
}
