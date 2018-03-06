package org.codegas.stores.test.builder;

import org.codegas.common.lang.spacial.GeoPoint;
import org.codegas.stores.domain.entity.Node;
import org.codegas.stores.domain.value.NodeType;

import org.codegas.common.lang.spacial.GeoPoint;
import org.codegas.stores.domain.entity.Node;
import org.codegas.stores.domain.value.NodeType;

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
