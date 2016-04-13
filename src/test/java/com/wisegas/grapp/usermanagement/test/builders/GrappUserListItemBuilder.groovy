package com.wisegas.grapp.usermanagement.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.usermanagement.domain.entity.GrappUserListItem
import com.wisegas.grapp.usermanagement.domain.value.Item

class GrappUserListItemBuilder {

   static unique = 0

   static GrappUserListItem grappUserListItem() {
      unique++
      EntityBuilder.wrapBuilder(new GrappUserListItem(
         GrappUserListBuilder.grappUserList(),
         new Item("#${unique}", "Item #${unique}")
      )) as GrappUserListItem
   }
}
