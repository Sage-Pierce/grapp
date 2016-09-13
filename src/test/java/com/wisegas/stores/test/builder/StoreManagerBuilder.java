package com.wisegas.stores.test.builder;

import com.wisegas.common.lang.value.Email;
import com.wisegas.stores.domain.entity.StoreManager;

public final class StoreManagerBuilder {

   private static int unique = 0;

   private StoreManagerBuilder() {

   }

   public static StoreManager build() {
      unique++;
      return new StoreManager(
         Email.fromString("test" + unique + "@email.com")
      );
   }
}
