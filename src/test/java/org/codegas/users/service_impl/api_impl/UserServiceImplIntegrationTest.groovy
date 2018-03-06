package org.codegas.users.service_impl.api_impl

import org.codegas.common.test.base.IntegrationTest
import org.codegas.users.domain.entity.User
import org.codegas.users.service.api.UserService
import org.codegas.users.test.builder.UserBuilder
import org.codegas.common.test.base.IntegrationTest
import org.codegas.users.domain.entity.User
import org.codegas.users.service.api.UserService
import org.codegas.users.test.builder.UserBuilder

import javax.inject.Inject

public class UserServiceImplIntegrationTest extends IntegrationTest {

   @Inject
   private UserService userService

   def "We can change a User's name and get back an appropriate DTO"() {
      given:
      User user = testEntityManager.save(UserBuilder.build())

      when:
      def result = userService.update(user.getEmail(), "NEW NAME")

      then:
      result.getName() == "NEW NAME"
   }
}