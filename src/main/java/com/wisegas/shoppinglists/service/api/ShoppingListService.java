package com.wisegas.shoppinglists.service.api;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.shoppinglists.service.dto.ShoppingListDto;
import com.wisegas.shoppinglists.service.dto.ShoppingListItemDto;

public interface ShoppingListService {
   ShoppingListDto get(String id);

   ShoppingListItemDto addItem(String id, CodeName item);

   void delete(String id);
}
