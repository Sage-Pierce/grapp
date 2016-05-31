package com.wisegas.stores.service.api;

import com.wisegas.stores.service.dto.ShoppingLayoutDto;
import com.wisegas.stores.service.dto.ShoppingListDto;

public interface ShoppingLayoutService {
   ShoppingLayoutDto getShoppingLayout(String id, ShoppingListDto shoppingList);
}
