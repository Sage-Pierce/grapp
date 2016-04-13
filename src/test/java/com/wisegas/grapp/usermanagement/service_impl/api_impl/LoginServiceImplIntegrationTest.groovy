package com.wisegas.grapp.usermanagement.service_impl.api_impl

import com.wisegas.common.test.IntegrationTest
import com.wisegas.grapp.usermanagement.domain.entity.User
import com.wisegas.grapp.usermanagement.service.api.LoginService
import com.wisegas.grapp.usermanagement.service.dto.UserDto
import com.wisegas.grapp.usermanagement.test.builders.UserBuilder

import javax.inject.Inject

class LoginServiceImplIntegrationTest extends IntegrationTest {

   @Inject
   private LoginService grappLoginService

   def "a GrappUser is created if not currently in the DB"() {
      when:
      UserDto userResource = grappLoginService.logIn("another@email.com", null)

      then:
      userResource != null
      userResource.getId() != null
   }

   def "a GrappUser will be found if it is already in the DB"() {
      User savedUser = testEntityManager.save(UserBuilder.user())

      when:
      UserDto userResource = grappLoginService.logIn(savedUser.getEmail(), null)

      then:
      userResource.getId() == savedUser.getId().toString()
   }
}
