package com.wisegas.grapp.test

import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem
import com.wisegas.grapp.storemanagement.domain.entity.GrappStore
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreLayout
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNode
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNodeItem
import com.wisegas.grapp.test.builders.*
import com.wisegas.grapp.usermanagement.domain.entity.GrappUser
import com.wisegas.grapp.usermanagement.domain.entity.GrappUserList

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
