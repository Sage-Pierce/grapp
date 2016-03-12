package com.wisegas.grapp.test

import com.wisegas.grapp.domain.entity.GrappItem
import com.wisegas.grapp.domain.entity.GrappStore
import com.wisegas.grapp.domain.entity.GrappStoreLayout
import com.wisegas.grapp.domain.entity.GrappStoreNode
import com.wisegas.grapp.domain.entity.GrappUser
import com.wisegas.grapp.domain.entity.GrappUserList
import com.wisegas.grapp.test.builders.GrappItemBuilder
import com.wisegas.grapp.test.builders.GrappStoreBuilder
import com.wisegas.grapp.test.builders.GrappStoreNodeBuilder
import com.wisegas.grapp.test.builders.GrappUserBuilder
import com.wisegas.grapp.test.builders.GrappUserListBuilder

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

   static GrappItem grappItem() {
      GrappItemBuilder.grappItem()
   }
}
