package com.wisegas.grapp.itemmanagement.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCodeType

class GrappItemBuilder {

   static unique = 0

   static GrappItem grappItem() {
      unique++
      EntityBuilder.wrapBuilder(new GrappItem(
         new GrappItemCode(GrappItemCodeType.MANUAL, "${unique}"),
         "Test Item ${unique}"
      )) as GrappItem
   }
}
