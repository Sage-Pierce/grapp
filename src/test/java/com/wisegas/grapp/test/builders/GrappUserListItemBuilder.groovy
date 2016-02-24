package com.wisegas.grapp.test.builders

import com.wisegas.grapp.domain.value.GrappUserListItem
import com.wisegas.test.EntityBuilder

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
