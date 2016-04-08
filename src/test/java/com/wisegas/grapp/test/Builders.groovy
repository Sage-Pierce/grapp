package com.wisegas.grapp.test

import com.wisegas.grapp.domain.entity.*
import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem
import com.wisegas.grapp.test.builders.*

class Builders {

   static GrappUser grappUser() {
      GrappUserBuilder.grappUser()
   }

   static GrappUserList grappUserList() {
      GrappUserListBuilder.grappUserList()
   }

   static GrappStore grappStore() {
      GrappStoreBuilder.grappStore()
   }

   static GrappStoreLayout grappStoreLayout() {
      GrappStoreBuilder.grappStore().getGrappStoreLayout()
   }

   static GrappStoreNode grappStoreNode() {
      GrappStoreNodeBuilder.grappStoreNode()
   }

   static GrappStoreNodeItem grappStoreNodeItem() {
      GrappStoreNodeItemBuilder.grappStoreNodeItem()
   }

   static GrappItem grappItem() {
      GrappItemBuilder.grappItem()
   }
}
