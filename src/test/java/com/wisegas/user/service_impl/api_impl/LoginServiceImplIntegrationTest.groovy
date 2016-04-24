package com.wisegas.user.service_impl.api_impl

import com.wisegas.common.test.IntegrationTest
import com.wisegas.user.domain.entity.User
import com.wisegas.user.service.api.LoginService
import com.wisegas.user.service.dto.UserDto
import com.wisegas.user.test.builders.UserBuilder

import javax.inject.Inject

class LoginServiceImplIntegrationTest extends IntegrationTest {

   @Inject
   private LoginService loginService

   def "a User is created if not currently in the DB"() {
      when:
      UserDto userResource = loginService.logIn("another@email.com", null)

      then:
      userResource != null
      userResource.getId() != null
   }

   def "a User will be found if it is already in the DB"() {
      User savedUser = testEntityManager.save(UserBuilder.user())

      when:
      UserDto userResource = loginService.logIn(savedUser.getId().toString(), null)

      then:
      userResource.getId() == savedUser.getId().toString()
   }
}
