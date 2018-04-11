package org.codegas.itemmanagement.test.builder;

import org.codegas.itemmanagement.domain.entity.Item;
import org.codegas.itemmanagement.domain.value.Code;
import org.codegas.itemmanagement.domain.value.CodeType;

public final class ItemBuilder {

   private static int unique = 0;

   private ItemBuilder() {

   }

   public static Item item() {
      unique++;
      return new Item(
         new Code(CodeType.MANUAL, "#" + unique),
         "Test Item " + unique
      );
   }
}
