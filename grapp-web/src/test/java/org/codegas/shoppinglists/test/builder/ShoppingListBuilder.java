package org.codegas.shoppinglists.test.builder;

import org.codegas.shoppinglists.domain.entity.ShoppingList;

public final class ShoppingListBuilder {

   private static int unique = 0;

   private ShoppingListBuilder() {

   }

   public static ShoppingList build() {
      unique++;
      return new ShoppingList(
         ShopperBuilder.build(),
         "Test Shopping List " + unique
      );
   }
}
