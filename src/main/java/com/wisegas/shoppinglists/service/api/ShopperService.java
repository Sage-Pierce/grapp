package com.wisegas.shoppinglists.service.api;

import com.wisegas.common.lang.value.Email;
import com.wisegas.shoppinglists.service.dto.ShopperDto;
import com.wisegas.shoppinglists.service.dto.ShoppingListDto;

public interface ShopperService {
   ShopperDto loadByEmail(Email email);

   ShoppingListDto addList(Email email, String name);
}
