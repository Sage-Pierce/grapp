package com.wisegas.stores.test.builder

import com.wisegas.common.lang.spacial.GeoPoint
import com.wisegas.common.test.builder.Builder
import com.wisegas.stores.domain.entity.Store

class StoreBuilder {

   static unique = 0

   static Store store() {
      unique++
      Builder.wrap(new Store(
         StoreManagerBuilder.storeManager(),
         "Test Store ${unique}",
         new GeoPoint(0, 0)
      ))
   }
}
