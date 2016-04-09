package com.wisegas.grapp.itemmanagement.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem

class GrappItemBuilder {

   static unique = 0

   static GrappItem grappItem() {
      unique++
      EntityBuilder.wrapBuilder(new GrappItem(
         "Test Item ${unique}"
      )) as GrappItem
   }
}
