package com.wisegas.stores.test.builders

import com.wisegas.common.lang.spacial.GeoPoint
import com.wisegas.common.test.util.Builder
import com.wisegas.stores.domain.entity.Store

class StoreBuilder {

   static unique = 0

   static Store store() {
      unique++
      Builder.wrapBuilder(new Store(
         StoreManagerBuilder.storeManager(),
         "Test Store ${unique}",
         new GeoPoint(0, 0)
      )) as Store
   }
}
