package com.wisegas.grapp.test.builders

import com.wisegas.grapp.domain.entity.GrappStore
import com.wisegas.lang.GeoPoint

class GrappStoreBuilder {

   static unique = 0

   static GrappStore grappStore() {
      unique++
      EntityBuilder.wrapBuilder(new GrappStore(
         GrappUserBuilder.grappUser(),
         "Test Store ${unique}",
         new GeoPoint(0, 0)
      ))
   }
}
