package com.wisegas.grapp.storemanagement.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.storemanagement.domain.entity.NodeItem
import com.wisegas.grapp.storemanagement.domain.value.Item

class GrappStoreNodeItemBuilder {

   static unique = 0

   static NodeItem grappStoreNodeItem() {
      unique++
      EntityBuilder.wrapBuilder(new NodeItem(
         GrappStoreNodeBuilder.grappStoreNode(),
         new Item("#${unique}", "Item #${unique}")
      )) as NodeItem
   }
}
