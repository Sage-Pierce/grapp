package com.wisegas.users.test.builders

import com.wisegas.common.lang.value.Email
import com.wisegas.common.test.util.Builder
import com.wisegas.users.domain.entity.User

class UserBuilder {

   static unique = 0

   static User user() {
      unique++
      Builder.wrapBuilder(new User(
         Email.fromString("test${unique}@email.com"),
         "Test User ${unique}",
         "<avatar ${unique}>"
      )) as User
   }
}