package org.codegas.users.test.builder;

import org.codegas.common.lang.value.Email;
import org.codegas.users.domain.entity.User;

import org.codegas.common.lang.value.Email;
import org.codegas.users.domain.entity.User;

public final class UserBuilder {

   private static int unique = 0;

   private UserBuilder() {

   }

   public static User build() {
      unique++;
      return new User(
         Email.fromString("test" + unique + "@email.com"),
         "Test User " + unique,
         "<avatar " + unique + ">"
      );
   }
}