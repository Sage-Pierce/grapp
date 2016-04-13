package com.wisegas.grapp.itemmanagement.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.itemmanagement.domain.entity.Item
import com.wisegas.grapp.itemmanagement.domain.value.Code
import com.wisegas.grapp.itemmanagement.domain.value.CodeType

class ItemBuilder {

   static unique = 0

   static Item grappItem() {
      unique++
      EntityBuilder.wrapBuilder(new Item(
            new Code(CodeType.MANUAL, "${unique}"),
            "Test Item ${unique}"
      )) as Item
   }
}
