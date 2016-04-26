package com.wisegas.storemanagement.test.builders

import com.wisegas.common.lang.spacial.GeoPoint
import com.wisegas.common.test.EntityBuilder
import com.wisegas.storemanagement.domain.entity.Node
import com.wisegas.storemanagement.domain.value.NodeType

class NodeBuilder {

   static unique = 0

   static Node node() {
      unique++
      EntityBuilder.wrapBuilder(new Node(
            LayoutBuilder.layout(),
            "Test Node ${unique}",
            NodeType.REGULAR,
            new GeoPoint(0, 0)
      )) as Node
   }
}
