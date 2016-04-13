package com.wisegas.grapp.usermanagement.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.usermanagement.domain.entity.ShoppingList

class GrappUserListBuilder {

   static unique = 0

   static ShoppingList grappUserList() {
      unique++
      EntityBuilder.wrapBuilder(new ShoppingList(
            GrappUserBuilder.grappUser(),
            "Test User List ${unique}"
      )) as ShoppingList
   }
}
