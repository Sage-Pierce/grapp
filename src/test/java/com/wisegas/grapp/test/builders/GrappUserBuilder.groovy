package com.wisegas.grapp.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.usermanagement.domain.entity.GrappUser

class GrappUserBuilder {

   static unique = 0

   static GrappUser grappUser() {
      unique++
      EntityBuilder.wrapBuilder(new GrappUser(
         "Test User ${unique}",
         "test@email${unique}.com",
         "<avatar ${unique}>"
      )) as GrappUser
   }
}