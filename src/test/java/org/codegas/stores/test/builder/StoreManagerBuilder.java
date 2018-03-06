package org.codegas.stores.test.builder;

import org.codegas.common.lang.value.Email;
import org.codegas.stores.domain.entity.StoreManager;

import org.codegas.common.lang.value.Email;
import org.codegas.stores.domain.entity.StoreManager;

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
