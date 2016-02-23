package com.wisegas.grapp.test.builders

import com.wisegas.grapp.domain.value.GrappStoreNodeLink

class GrappStoreNodeLinkBuilder {

   static GrappStoreNodeLink grappStoreNodeLink() {
      EntityBuilder.wrapBuilder(new GrappStoreNodeLink(
         GrappStoreNodeBuilder.grappStoreNode(),
         GrappStoreNodeBuilder.grappStoreNode()
      ))
   }
}
