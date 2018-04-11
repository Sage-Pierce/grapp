package org.codegas.stores.service_impl.factory;

import java.util.List;

import org.codegas.stores.domain.entity.Node;
import org.codegas.stores.service.dto.ShoppingItemDto;
import org.codegas.stores.service.dto.ShoppingNodeDto;

public final class ShoppingNodeDtoFactory {

    private ShoppingNodeDtoFactory() {

    }

    public static ShoppingNodeDto createDto(Node node, List<ShoppingItemDto> items) {
        ShoppingNodeDto shoppingNodeDto = new ShoppingNodeDto();
        shoppingNodeDto.setId(node.getId().toString());
        shoppingNodeDto.setName(node.getName());
        shoppingNodeDto.setType(node.getType().name());
        shoppingNodeDto.setLocation(node.getLocation());
        shoppingNodeDto.setItems(items);
        return shoppingNodeDto;
    }
}
