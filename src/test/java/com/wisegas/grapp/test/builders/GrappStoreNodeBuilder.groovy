package com.wisegas.grapp.test.builders

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNode
import com.wisegas.grapp.storemanagement.domain.value.GrappStoreNodeType

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
