package com.wisegas.shoppinglists.test.builder;

import com.wisegas.shoppinglists.domain.entity.ShoppingList;

public final class ShoppingListBuilder {

   private static int unique = 0;

   public static ShoppingList build() {
      unique++;
      return new ShoppingList(
         ShopperBuilder.build(),
         "Test Shopping List " + unique
      );
   }

   private ShoppingListBuilder() {

   }
}
