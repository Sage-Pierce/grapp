package com.wisegas.grapp.test.builders

import com.wisegas.grapp.domain.entity.GrappStore
import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.test.EntityBuilder

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
