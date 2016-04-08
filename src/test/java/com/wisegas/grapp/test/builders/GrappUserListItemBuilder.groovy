package com.wisegas.grapp.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.domain.entity.GrappUserListItem

class GrappUserListItemBuilder {

   static unique = 0

   static GrappUserListItem grappUserListItem() {
      unique++
      EntityBuilder.wrapBuilder(new GrappUserListItem(
         GrappUserListBuilder.grappUserList(),
         GrappItemBuilder.grappItem()
      ))
   }
}
