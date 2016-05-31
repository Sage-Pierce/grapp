package com.wisegas.stores.test.builders

import com.wisegas.common.test.entity.EntityBuilder
import com.wisegas.stores.domain.entity.NodeItem
import com.wisegas.stores.domain.value.Item

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
