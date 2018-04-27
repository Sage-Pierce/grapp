package org.codegas.shoppinglists.service.api;

import org.codegas.commons.lang.value.PrincipalName;
import org.codegas.shoppinglists.service.dto.ShopperDto;
import org.codegas.shoppinglists.service.dto.ShoppingListDto;

public interface ShopperService {

    ShopperDto load(PrincipalName name);

    ShoppingListDto addList(PrincipalName shopperName, String listName);
}
