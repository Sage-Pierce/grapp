package org.codegas.stores.service.api;

import org.codegas.stores.service.dto.ShoppingLayoutDto;
import org.codegas.stores.service.dto.ShoppingListDto;

public interface ShoppingLayoutService {

    ShoppingLayoutDto getShoppingLayout(String id, ShoppingListDto shoppingListDto);
}
