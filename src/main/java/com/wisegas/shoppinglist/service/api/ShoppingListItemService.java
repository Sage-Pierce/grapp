package com.wisegas.shoppinglist.service.api;

import com.wisegas.shoppinglist.service.dto.ShoppingListItemDto;

public interface ShoppingListItemService {
   ShoppingListItemDto get(String id);

   void delete(String id);
}
