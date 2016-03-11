package com.wisegas.grapp.test.builders

import com.wisegas.grapp.domain.value.GrappStoreNodeItem
import com.wisegas.common.test.EntityBuilder

class GrappStoreNodeItemBuilder {

   static GrappStoreNodeItem grappStoreNodeItem() {
      EntityBuilder.wrapBuilder(new GrappStoreNodeItem(
         GrappStoreNodeBuilder.grappStoreNode(),
         GrappItemBuilder.grappItem()
      ))
   }
}
