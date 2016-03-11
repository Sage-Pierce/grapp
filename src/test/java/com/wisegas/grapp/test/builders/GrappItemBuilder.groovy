package com.wisegas.grapp.test.builders

import com.wisegas.grapp.domain.entity.GrappItem
import com.wisegas.common.test.EntityBuilder

class GrappItemBuilder {

   static unique = 0

   static GrappItem grappItem() {
      unique++
      EntityBuilder.wrapBuilder(new GrappItem(
         "Test Item ${unique}"
      ))
   }
}
