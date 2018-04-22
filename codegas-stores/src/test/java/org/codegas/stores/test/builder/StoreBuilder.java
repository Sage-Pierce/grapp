package org.codegas.stores.test.builder;

import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.stores.domain.entity.Store;

public final class StoreBuilder {

   private static int unique = 0;

   private StoreBuilder() {

   }

   public static Store build() {
      unique++;
      return new Store(
         StoreManagerBuilder.build(),
         "Test Store " + unique,
         new GeoPoint(0, 0)
      );
   }
}
