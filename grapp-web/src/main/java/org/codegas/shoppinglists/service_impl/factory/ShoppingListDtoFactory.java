package org.codegas.shoppinglists.service_impl.factory;

import java.util.stream.Collectors;

import org.codegas.shoppinglists.domain.entity.ShoppingList;
import org.codegas.shoppinglists.service.dto.ShoppingListDto;

public final class ShoppingListDtoFactory {

    private ShoppingListDtoFactory() {

    }

    public static ShoppingListDto createDto(ShoppingList shoppingList) {
        ShoppingListDto shoppingListDto = new ShoppingListDto();
        shoppingListDto.setId(shoppingList.getId().toString());
        shoppingListDto.setName(shoppingList.getName());
        shoppingListDto.setItems(shoppingList.getItems().stream().map(ShoppingListItemDtoFactory::createDto).collect(Collectors.toList()));
        return shoppingListDto;
    }
}
