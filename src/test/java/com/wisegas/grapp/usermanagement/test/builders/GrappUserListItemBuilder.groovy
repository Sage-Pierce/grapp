package com.wisegas.grapp.usermanagement.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.usermanagement.domain.entity.GrappUserListItem

class GrappUserListItemBuilder {

   static unique = 0

   static GrappUserListItem grappUserListItem() {
      unique++
      EntityBuilder.wrapBuilder(new GrappUserListItem(
            GrappUserListBuilder.grappUserList(),
            "#${unique}",
            "Item #${unique}"
      )) as GrappUserListItem
   }
}
