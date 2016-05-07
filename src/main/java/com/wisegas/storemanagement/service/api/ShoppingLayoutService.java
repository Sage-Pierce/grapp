package com.wisegas.storemanagement.service.api;

import com.wisegas.storemanagement.service.dto.ShoppingLayoutDto;
import com.wisegas.storemanagement.service.dto.ShoppingListDto;

public interface ShoppingLayoutService {
   ShoppingLayoutDto getShoppingLayout(String id, ShoppingListDto shoppingList);
}
