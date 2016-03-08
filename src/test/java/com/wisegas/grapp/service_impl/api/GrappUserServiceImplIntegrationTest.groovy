package com.wisegas.grapp.service_impl.api

import com.wisegas.grapp.domain.entity.GrappUser
import com.wisegas.grapp.service.api.GrappUserService
import com.wisegas.grapp.test.Builders;
import com.wisegas.test.BaseIntegrationTest

import javax.inject.Inject

public class GrappUserServiceImplIntegrationTest extends BaseIntegrationTest {

   @Inject
   private GrappUserService grappUserService

   def "We can change a GrappUser's name and get back an appropriate DTO"() {
      given:
      GrappUser grappUser = testEntityManager.save(Builders.grappUser())

      when:
      def result = grappUserService.updateName(grappUser.getId().toString(), "NEW NAME")

      then:
      result.getName() == "NEW NAME"
   }
}