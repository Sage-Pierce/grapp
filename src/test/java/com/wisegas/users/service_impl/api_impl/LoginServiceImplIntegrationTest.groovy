package com.wisegas.users.service_impl.api_impl

import com.wisegas.common.lang.value.Email
import com.wisegas.common.test.base.PersistenceTest
import com.wisegas.users.domain.entity.User
import com.wisegas.users.service.api.LoginService
import com.wisegas.users.service.dto.UserDto
import com.wisegas.users.test.builder.UserBuilder

import javax.inject.Inject

class LoginServiceImplIntegrationTest extends PersistenceTest {

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
      User savedUser = testEntityManager.save(UserBuilder.user())

      when:
      UserDto userDto = loginService.logIn(savedUser.getId(), null)

      then:
      userDto.getEmail() == savedUser.getEmail().toString()
   }
}
