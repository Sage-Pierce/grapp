package org.codegas.shoppinglists.service.api;

import org.codegas.commons.lang.value.CodeName;
import org.codegas.shoppinglists.service.dto.ShoppingListDto;
import org.codegas.shoppinglists.service.dto.ShoppingListItemDto;

public interface ShoppingListService {

    ShoppingListDto get(String id);

    ShoppingListItemDto addItem(String id, CodeName item);

    void delete(String id);
}
