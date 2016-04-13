package com.wisegas.storemanagement.test.builders

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.test.EntityBuilder
import com.wisegas.storemanagement.domain.entity.Store

class StoreBuilder {

   static unique = 0

   static Store store() {
      unique++
      EntityBuilder.wrapBuilder(new Store(
         "Test Store ${unique}",
         new GeoPoint(0, 0)
      )) as Store
   }
}
