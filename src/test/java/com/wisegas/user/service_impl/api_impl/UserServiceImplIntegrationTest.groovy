package com.wisegas.user.service_impl.api_impl

import com.wisegas.common.test.IntegrationTest
import com.wisegas.user.domain.entity.User
import com.wisegas.user.service.api.UserService
import com.wisegas.user.test.builders.UserBuilder

import javax.inject.Inject

public class UserServiceImplIntegrationTest extends IntegrationTest {

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