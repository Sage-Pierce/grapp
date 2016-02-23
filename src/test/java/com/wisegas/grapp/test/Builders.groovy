package com.wisegas.grapp.test

import com.wisegas.grapp.domain.entity.GrappItem
import com.wisegas.grapp.domain.entity.GrappStore
import com.wisegas.grapp.domain.entity.GrappStoreNode
import com.wisegas.grapp.domain.entity.GrappUser
import com.wisegas.grapp.domain.entity.GrappUserList
import com.wisegas.grapp.domain.value.GrappStoreNodeItem
import com.wisegas.grapp.domain.value.GrappUserListItem
import com.wisegas.grapp.test.builders.GrappItemBuilder
import com.wisegas.grapp.test.builders.GrappStoreBuilder
import com.wisegas.grapp.test.builders.GrappStoreNodeBuilder
import com.wisegas.grapp.test.builders.GrappStoreNodeItemBuilder
import com.wisegas.grapp.test.builders.GrappUserBuilder
import com.wisegas.grapp.test.builders.GrappUserListBuilder
import com.wisegas.grapp.test.builders.GrappUserListItemBuilder

class Builders {

   static GrappUser grappUser() {
      GrappUserBuilder.grappUser()
   }

   static GrappUserList grappUserList() {
      GrappUserListBuilder.grappUserList()
   }

   static GrappUserListItem grappUserListItem() {
      GrappUserListItemBuilder.grappUserListItem()
   }

   static GrappStore grappStore() {
      GrappStoreBuilder.grappStore()
   }

   static GrappStoreNode grappStoreNode() {
      GrappStoreNodeBuilder.grappStoreNode()
   }

   static GrappItem grappItem() {
      GrappItemBuilder.grappItem()
   }

   static GrappStoreNodeItem grappStoreNodeItem() {
      GrappStoreNodeItemBuilder.grappStoreNodeItem()
   }
}
