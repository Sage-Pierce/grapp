package org.codegas.shoppinglists.service.api;

import org.codegas.common.lang.value.Email;
import org.codegas.shoppinglists.service.dto.ShopperDto;
import org.codegas.shoppinglists.service.dto.ShoppingListDto;

public interface ShopperService {

    ShopperDto loadByEmail(Email email);

    ShoppingListDto addList(Email email, String name);
}
