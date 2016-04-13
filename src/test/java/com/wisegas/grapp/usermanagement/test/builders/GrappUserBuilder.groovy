package com.wisegas.grapp.usermanagement.test.builders

import com.wisegas.common.test.EntityBuilder
import com.wisegas.grapp.usermanagement.domain.entity.User

class GrappUserBuilder {

   static unique = 0

   static User grappUser() {
      unique++
      EntityBuilder.wrapBuilder(new User(
         "test${unique}@email.com",
         "Test User ${unique}",
         "<avatar ${unique}>"
      )) as User
   }
}