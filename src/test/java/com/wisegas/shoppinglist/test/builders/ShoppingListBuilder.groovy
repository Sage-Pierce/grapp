package com.wisegas.shoppinglist.test.builders

import com.wisegas.common.test.entity.EntityBuilder
import com.wisegas.shoppinglist.domain.entity.ShoppingList

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
