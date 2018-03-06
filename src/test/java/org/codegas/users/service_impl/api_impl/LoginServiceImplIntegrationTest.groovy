package org.codegas.users.service_impl.api_impl

import org.codegas.commons.lang.value.Email
import org.codegas.commons.test.base.IntegrationTest
import org.codegas.users.domain.entity.User
import org.codegas.users.service.api.LoginService
import org.codegas.users.service.dto.UserDto
import org.codegas.users.test.builder.UserBuilder

import javax.inject.Inject

class LoginServiceImplIntegrationTest extends IntegrationTest {

   @Inject
   private LoginService loginService

   def "a User is created if not currently in the DB"() {
      when:
      UserDto userDto = loginService.logIn(Email.fromString("another@email.com"), null)

      then:
      userDto != null
      userDto.getEmail() != null
   }

   def "a User will be found if it is already in the DB"() {
      User savedUser = testEntityManager.save(UserBuilder.build())

      when:
      UserDto userDto = loginService.logIn(savedUser.getId(), null)

      then:
      userDto.getEmail() == savedUser.getEmail().toString()
   }
}
