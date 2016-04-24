package com.wisegas.shoppinglist.test.builders

import com.wisegas.common.lang.jpa.Email
import com.wisegas.common.test.EntityBuilder
import com.wisegas.shoppinglist.domain.entity.Shopper;

class ShopperBuilder {

   static unique = 0

   static Shopper shopper() {
      unique++
      EntityBuilder.wrapBuilder(new Shopper(
         Email.fromString("Test${unique}@email.com"),
      )) as Shopper
   }
}
