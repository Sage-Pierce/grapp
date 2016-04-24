package com.wisegas.shoppinglist.service.api;

import com.wisegas.common.lang.value.CodeName;
import com.wisegas.shoppinglist.service.dto.ShoppingListDto;
import com.wisegas.shoppinglist.service.dto.ShoppingListItemDto;

public interface ShoppingListService {
   ShoppingListDto get(String id);

   ShoppingListItemDto addItem(String id, CodeName item);

   void delete(String id);
}
