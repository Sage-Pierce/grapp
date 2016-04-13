package com.wisegas.grapp.usermanagement.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.usermanagement.domain.entity.ShoppingListItem
import com.wisegas.grapp.usermanagement.domain.value.Item

class GrappUserListItemBuilder {

   static unique = 0

   static ShoppingListItem grappUserListItem() {
      unique++
      EntityBuilder.wrapBuilder(new ShoppingListItem(
         GrappUserListBuilder.grappUserList(),
         new Item("#${unique}", "Item #${unique}")
      )) as ShoppingListItem
   }
}
