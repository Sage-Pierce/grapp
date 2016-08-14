package com.wisegas.stores.test.builder

import com.wisegas.stores.domain.entity.NodeItem
import com.wisegas.stores.domain.value.Item

class NodeItemBuilder {

   static unique = 0

   static NodeItem nodeItem() {
      unique++
      new NodeItem(
         NodeBuilder.node(),
         new Item("#${unique}", "Item #${unique}")
      )
   }
}
