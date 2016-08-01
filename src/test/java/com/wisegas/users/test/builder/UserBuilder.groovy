package com.wisegas.users.test.builder

import com.wisegas.common.lang.value.Email
import com.wisegas.common.test.builder.Builder
import com.wisegas.users.domain.entity.User

class UserBuilder {

   static unique = 0

   static User user() {
      unique++
      Builder.wrapBuilder(new User(
         Email.fromString("test${unique}@email.com"),
         "Test User ${unique}",
         "<avatar ${unique}>"
      ))
   }
}