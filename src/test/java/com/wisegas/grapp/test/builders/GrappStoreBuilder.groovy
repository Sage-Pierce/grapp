package com.wisegas.grapp.test.builders

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.storemanagement.domain.entity.GrappStore

class GrappStoreBuilder {

   static unique = 0

   static GrappStore grappStore() {
      unique++
      EntityBuilder.wrapBuilder(new GrappStore(
         "Test Store ${unique}",
         new GeoPoint(0, 0)
      ))
   }
}
