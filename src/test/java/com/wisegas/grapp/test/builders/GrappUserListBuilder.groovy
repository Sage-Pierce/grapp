package com.wisegas.grapp.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.usermanagement.domain.entity.GrappUserList

class GrappUserListBuilder {

   static unique = 0

   static GrappUserList grappUserList() {
      unique++
      EntityBuilder.wrapBuilder(new GrappUserList(
            GrappUserBuilder.grappUser(),
            "Test User List ${unique}"
      )) as GrappUserList
   }
}
