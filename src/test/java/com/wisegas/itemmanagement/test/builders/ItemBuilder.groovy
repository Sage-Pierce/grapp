package com.wisegas.itemmanagement.test.builders

import com.wisegas.common.test.util.Builder
import com.wisegas.itemmanagement.domain.entity.Item
import com.wisegas.itemmanagement.domain.value.Code
import com.wisegas.itemmanagement.domain.value.CodeType

class ItemBuilder {

   static unique = 0

   static Item item() {
      unique++
      Builder.wrapBuilder(new Item(
            new Code(CodeType.MANUAL, "${unique}"),
            "Test Item ${unique}"
      )) as Item
   }
}
