package com.wisegas.shoppinglists.test.builder

import com.wisegas.common.test.builder.Builder
import com.wisegas.shoppinglists.domain.entity.ShoppingList

class ShoppingListBuilder {

   static unique = 0

   static ShoppingList shoppingList() {
      unique++
      Builder.wrap(new ShoppingList(
         ShopperBuilder.shopper(),
         "Test Shopping List ${unique}"
      ))
   }
}
