package com.wisegas.shoppinglists.service.api;

import com.wisegas.shoppinglists.service.dto.ShoppingListItemDto;

public interface ShoppingListItemService {
   ShoppingListItemDto get(String id);

   void delete(String id);
}
