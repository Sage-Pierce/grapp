package org.codegas.shoppinglists.service_impl.factory;

import org.codegas.shoppinglists.domain.entity.ShoppingListItem;
import org.codegas.shoppinglists.service.dto.ShoppingListItemDto;

public final class ShoppingListItemDtoFactory {

    private ShoppingListItemDtoFactory() {

    }

    public static ShoppingListItemDto createDto(ShoppingListItem shoppingListItem) {
        ShoppingListItemDto shoppingListItemDto = new ShoppingListItemDto();
        shoppingListItemDto.setId(shoppingListItem.getId().toString());
        shoppingListItemDto.setItem(shoppingListItem.getItem().toCodeName());
        shoppingListItemDto.setObtained(shoppingListItem.isObtained());
        return shoppingListItemDto;
    }
}
