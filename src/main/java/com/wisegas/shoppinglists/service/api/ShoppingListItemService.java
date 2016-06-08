package com.wisegas.shoppinglists.service.api;

import com.wisegas.shoppinglists.service.dto.ShoppingListItemDto;

public interface ShoppingListItemService {
   ShoppingListItemDto get(String id);

   ShoppingListItemDto update(String id, boolean obtained);

   void delete(String id);
}
