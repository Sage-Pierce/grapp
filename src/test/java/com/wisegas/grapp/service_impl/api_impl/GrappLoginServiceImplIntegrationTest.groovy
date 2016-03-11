package com.wisegas.grapp.service_impl.api_impl

import com.wisegas.grapp.domain.entity.GrappUser
import com.wisegas.grapp.service.api.GrappLoginService
import com.wisegas.grapp.service.dto.GrappUserDTO
import com.wisegas.grapp.test.Builders
import com.wisegas.common.test.BaseIntegrationTest

import javax.inject.Inject

class GrappLoginServiceImplIntegrationTest extends BaseIntegrationTest {

   @Inject
   private GrappLoginService grappLoginService

   def "a GrappUser is created if not currently in the DB"() {
      when:
      GrappUserDTO userResource = grappLoginService.logIn("another@email.com", null)

      then:
      userResource != null
      userResource.getId() != null
      userResource.getEmail() == "another@email.com"
   }

   def "a GrappUser will be found if it is already in the DB"() {
      GrappUser savedUser = testEntityManager.save(Builders.grappUser().having { it.email = "test@test.com" })

      when:
      GrappUserDTO userResource = grappLoginService.logIn(savedUser.getEmail(), null)

      then:
      userResource.getId() == savedUser.getId().toString()
   }
}
