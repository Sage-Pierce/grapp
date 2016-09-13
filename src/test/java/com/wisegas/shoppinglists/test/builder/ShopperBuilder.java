package com.wisegas.shoppinglists.test.builder;

import com.wisegas.common.lang.value.Email;
import com.wisegas.shoppinglists.domain.entity.Shopper;

public final class ShopperBuilder {

   private static int unique = 0;

   private ShopperBuilder() {

   }

   public static Shopper build() {
      unique++;
      return new Shopper(
         Email.fromString("Test" + unique + "@email.com")
      );
   }
}
