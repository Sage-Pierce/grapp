package com.wisegas.shoppinglists.test.builders

import com.wisegas.common.test.entity.EntityBuilder
import com.wisegas.shoppinglists.domain.entity.ShoppingList

class ShoppingListBuilder {

   static unique = 0

   static ShoppingList shoppingList() {
      unique++
      EntityBuilder.wrapBuilder(new ShoppingList(
         ShopperBuilder.shopper(),
         "Test Shopping List ${unique}"
      )) as ShoppingList
   }
}
