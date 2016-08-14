package com.wisegas.stores.test.builder

import com.wisegas.common.lang.spacial.GeoPoint
import com.wisegas.stores.domain.entity.Store

class StoreBuilder {

   static unique = 0

   static Store store() {
      unique++
      new Store(
         StoreManagerBuilder.storeManager(),
         "Test Store ${unique}",
         new GeoPoint(0, 0)
      )
   }
}
