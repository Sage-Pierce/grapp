package com.wisegas.grapp.test.builders

import com.wisegas.grapp.domain.entity.GrappStoreNode
import com.wisegas.lang.GeoPoint
import com.wisegas.test.EntityBuilder

class GrappStoreNodeBuilder {

   static unique = 0

   static GrappStoreNode grappStoreNode() {
      unique++
      EntityBuilder.wrapBuilder(new GrappStoreNode(
         GrappStoreBuilder.grappStore().getGrappStoreLayout(),
         new GeoPoint(0, 0),
         "Test Node ${unique}"
      ))
   }
}
