package com.wisegas.stores.test.builder;

import com.wisegas.stores.domain.entity.NodeItem;
import com.wisegas.stores.domain.value.Item;

public final class NodeItemBuilder {

   private static int unique = 0;

   public static NodeItem build() {
      unique++;
      return new NodeItem(
         NodeBuilder.build(),
         new Item("#" + unique, "Item #" + unique)
      );
   }

   private NodeItemBuilder() {

   }
}
