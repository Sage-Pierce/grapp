package com.wisegas.shoppinglists.test.builder

import com.wisegas.common.lang.value.Email
import com.wisegas.common.test.builder.Builder
import com.wisegas.shoppinglists.domain.entity.Shopper;

class ShopperBuilder {

   static unique = 0

   static Shopper shopper() {
      unique++
      Builder.wrap(new Shopper(
         Email.fromString("Test${unique}@email.com"),
      ))
   }
}
