package com.wisegas.grapp.test.builders

import com.wisegas.grapp.domain.entity.GrappStoreNode
import com.wisegas.grapp.domain.value.GrappStoreNodeType
import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.test.EntityBuilder

class GrappStoreNodeBuilder {

   static unique = 0

   static GrappStoreNode grappStoreNode() {
      unique++
      EntityBuilder.wrapBuilder(new GrappStoreNode(
            GrappStoreBuilder.grappStore().getGrappStoreLayout(),
            "Test Node ${unique}",
            GrappStoreNodeType.REGULAR,
            new GeoPoint(0, 0)
      ))
   }
}
