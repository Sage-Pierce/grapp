package com.wisegas.stores.test.builder;

import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.stores.domain.entity.Node;
import com.wisegas.stores.domain.value.NodeType;

public final class NodeBuilder {

   private static int unique = 0;

   private NodeBuilder() {

   }

   public static Node build() {
      unique++;
      return new Node(
         StoreLayoutBuilder.build(),
         "Test Node " + unique,
         NodeType.REGULAR,
         new GeoPoint(0, 0)
      );
   }
}
