package org.codegas.shoppinglists.service.api;

import org.codegas.shoppinglists.service.dto.ShoppingListItemDto;

public interface ShoppingListItemService {

    ShoppingListItemDto get(String id);

    ShoppingListItemDto update(String id, boolean obtained);

    void delete(String id);
}
