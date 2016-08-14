package com.wisegas.stores.test.builder;

import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.stores.domain.entity.Store;

public final class StoreBuilder {

   private static int unique = 0;

   public static Store build() {
      unique++;
      return new Store(
         StoreManagerBuilder.build(),
         "Test Store " + unique,
         new GeoPoint(0, 0)
      );
   }

   private StoreBuilder() {

   }
}
