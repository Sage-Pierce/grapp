package com.wisegas.grapp.usermanagement.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.usermanagement.domain.entity.GrappUser

class GrappUserBuilder {

   static unique = 0

   static GrappUser grappUser() {
      unique++
      EntityBuilder.wrapBuilder(new GrappUser(
         "test${unique}@email.com",
         "Test User ${unique}",
         "<avatar ${unique}>"
      )) as GrappUser
   }
}