package com.wisegas.stores.test.builder

import com.wisegas.common.test.builder.Builder
import com.wisegas.stores.domain.entity.NodeItem
import com.wisegas.stores.domain.value.Item

class NodeItemBuilder {

   static unique = 0

   static NodeItem nodeItem() {
      unique++
      Builder.wrap(new NodeItem(
            NodeBuilder.node(),
            new Item("#${unique}", "Item #${unique}")
      ))
   }
}
