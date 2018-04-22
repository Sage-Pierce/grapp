package org.codegas.stores.test.builder;

import org.codegas.stores.domain.entity.NodeItem;
import org.codegas.stores.domain.value.Item;

public final class NodeItemBuilder {

   private static int unique = 0;

   private NodeItemBuilder() {

   }

   public static NodeItem build() {
      unique++;
      return new NodeItem(
         NodeBuilder.build(),
         new Item("#" + unique, "Item #" + unique)
      );
   }
}
