package com.wisegas.shoppinglist.service.api;

import com.wisegas.common.lang.value.Email;
import com.wisegas.shoppinglist.service.dto.ShopperDto;
import com.wisegas.shoppinglist.service.dto.ShoppingListDto;

public interface ShopperService {
   ShopperDto loadByEmail(Email email);

   ShoppingListDto addList(Email email, String name);
}
