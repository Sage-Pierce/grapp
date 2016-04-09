package com.wisegas.grapp.storemanagement.test.builders

import com.wisegas.common.lang.value.IdName
import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNodeItem

class GrappStoreNodeItemBuilder {

   static unique = 0

   static GrappStoreNodeItem grappStoreNodeItem() {
      unique++
      EntityBuilder.wrapBuilder(new GrappStoreNodeItem(
         GrappStoreNodeBuilder.grappStoreNode(),
         new IdName(unique.toString(), "Item #${unique}")
      )) as GrappStoreNodeItem
   }
}
