package com.wisegas.users.service_impl.api_impl

import com.wisegas.common.test.base.PersistenceTest
import com.wisegas.users.domain.entity.User
import com.wisegas.users.service.api.UserService
import com.wisegas.users.test.builder.UserBuilder

import javax.inject.Inject

public class UserServiceImplIntegrationTest extends PersistenceTest {

   @Inject
   private UserService userService

   def "We can change a User's name and get back an appropriate DTO"() {
      given:
      User user = testEntityManager.save(UserBuilder.user())

      when:
      def result = userService.update(user.getEmail(), "NEW NAME")

      then:
      result.getName() == "NEW NAME"
   }
}