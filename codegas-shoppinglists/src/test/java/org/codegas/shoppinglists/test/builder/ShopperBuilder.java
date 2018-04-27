package org.codegas.shoppinglists.test.builder;

import org.codegas.commons.lang.value.PrincipalName;
import org.codegas.shoppinglists.domain.entity.Shopper;

public final class ShopperBuilder {

   private static int unique = 0;

   private ShopperBuilder() {

   }

   public static Shopper build() {
      unique++;
      return new Shopper(
         PrincipalName.fromString("Test" + unique + "@email.com")
      );
   }
}
