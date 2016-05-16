package com.wisegas.storemanagement.test.builders

import com.wisegas.common.lang.spacial.GeoPoint
import com.wisegas.common.test.entity.EntityBuilder
import com.wisegas.storemanagement.domain.entity.Store

class StoreBuilder {

   static unique = 0

   static Store store() {
      unique++
      EntityBuilder.wrapBuilder(new Store(
         StoreManagerBuilder.storeManager(),
         "Test Store ${unique}",
         new GeoPoint(0, 0)
      )) as Store
   }
}
