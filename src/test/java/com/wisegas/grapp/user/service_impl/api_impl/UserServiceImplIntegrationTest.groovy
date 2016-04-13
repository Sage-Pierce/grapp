package com.wisegas.grapp.user.service_impl.api_impl

import com.wisegas.common.test.IntegrationTest
import com.wisegas.grapp.user.domain.entity.User
import com.wisegas.grapp.user.service.api.UserService
import com.wisegas.grapp.user.test.builders.UserBuilder

import javax.inject.Inject

public class UserServiceImplIntegrationTest extends IntegrationTest {

   @Inject
   private UserService grappUserService

   def "We can change a GrappUser's name and get back an appropriate DTO"() {
      given:
      User grappUser = testEntityManager.save(UserBuilder.user())

      when:
      def result = grappUserService.update(grappUser.getId().toString(), "NEW NAME")

      then:
      result.getName() == "NEW NAME"
   }
}