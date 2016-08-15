package com.wisegas.itemmanagement.test.builder;

import com.wisegas.itemmanagement.domain.entity.Item;
import com.wisegas.itemmanagement.domain.value.Code;
import com.wisegas.itemmanagement.domain.value.CodeType;

public final class ItemBuilder {

   private static int unique = 0;

   public static Item item() {
      unique++;
      return new Item(
         new Code(CodeType.MANUAL, "#" + unique),
         "Test Item " + unique
      );
   }

   private ItemBuilder() {

   }
}
