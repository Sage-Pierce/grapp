package com.wisegas.users.test.builder;

import com.wisegas.common.lang.value.Email;
import com.wisegas.users.domain.entity.User;

public final class UserBuilder {

   private static int unique = 0;

   public static User build() {
      unique++;
      return new User(
         Email.fromString("test" + unique + "@email.com"),
         "Test User " + unique,
         "<avatar " + unique + ">"
      );
   }

   private UserBuilder() {

   }
}