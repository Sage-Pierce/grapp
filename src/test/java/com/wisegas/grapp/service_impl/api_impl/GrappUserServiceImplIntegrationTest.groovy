package com.wisegas.grapp.service_impl.api_impl

import com.wisegas.common.test.IntegrationTest
import com.wisegas.grapp.test.Builders
import com.wisegas.grapp.usermanagement.domain.entity.GrappUser
import com.wisegas.grapp.usermanagement.service.api.GrappUserService

import javax.inject.Inject

public class GrappUserServiceImplIntegrationTest extends IntegrationTest {

   @Inject
   private GrappUserService grappUserService

   def "We can change a GrappUser's name and get back an appropriate DTO"() {
      given:
      GrappUser grappUser = testEntityManager.save(Builders.grappUser())

      when:
      def result = grappUserService.update(grappUser.getId().toString(), "NEW NAME")

      then:
      result.getName() == "NEW NAME"
   }
}