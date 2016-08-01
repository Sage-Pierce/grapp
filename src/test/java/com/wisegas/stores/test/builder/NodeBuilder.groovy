package com.wisegas.stores.test.builder

import com.wisegas.common.lang.spacial.GeoPoint
import com.wisegas.common.test.builder.Builder
import com.wisegas.stores.domain.entity.Node
import com.wisegas.stores.domain.value.NodeType

class NodeBuilder {

   static unique = 0

   static Node node() {
      unique++
      Builder.wrapBuilder(new Node(
            StoreLayoutBuilder.storeLayout(),
            "Test Node ${unique}",
            NodeType.REGULAR,
            new GeoPoint(0, 0)
      ))
   }
}
