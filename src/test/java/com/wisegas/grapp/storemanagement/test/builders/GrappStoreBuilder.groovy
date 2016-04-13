package com.wisegas.grapp.storemanagement.test.builders

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.storemanagement.domain.entity.Store

class GrappStoreBuilder {

   static unique = 0

   static Store grappStore() {
      unique++
      EntityBuilder.wrapBuilder(new Store(
         "Test Store ${unique}",
         new GeoPoint(0, 0)
      )) as Store
   }
}
