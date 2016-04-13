package com.wisegas.grapp.storemanagement.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.storemanagement.domain.entity.NodeItem
import com.wisegas.grapp.storemanagement.domain.value.Item

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
