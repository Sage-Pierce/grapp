package com.wisegas.stores.test.builder

import com.wisegas.common.lang.spacial.GeoPoint
import com.wisegas.stores.domain.entity.Node
import com.wisegas.stores.domain.value.NodeType

class NodeBuilder {

   static unique = 0

   static Node node() {
      unique++
      new Node(
         StoreLayoutBuilder.storeLayout(),
         "Test Node ${unique}",
         NodeType.REGULAR,
         new GeoPoint(0, 0)
      )
   }
}
