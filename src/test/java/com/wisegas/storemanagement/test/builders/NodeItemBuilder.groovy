package com.wisegas.storemanagement.test.builders

import com.wisegas.common.test.entity.EntityBuilder
import com.wisegas.storemanagement.domain.entity.NodeItem
import com.wisegas.storemanagement.domain.value.Item

class NodeItemBuilder {

   static unique = 0

   static NodeItem nodeItem() {
      unique++
      EntityBuilder.wrapBuilder(new NodeItem(
            NodeBuilder.node(),
            new Item("#${unique}", "Item #${unique}")
      )) as NodeItem
   }
}
