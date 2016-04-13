package com.wisegas.grapp.usermanagement.service_impl.factory

import com.wisegas.grapp.usermanagement.domain.entity.GrappUser
import spock.lang.Specification

class GrappUserDtoFactoryTest extends Specification {

   def "A GrappUserResource can be created from a GrappUser"() {
      given:
      GrappUser grappUser = new GrappUser("Test_Email", "Test", "Test_Avatar")

      when:
      def result = GrappUserDtoFactory.createDto(grappUser)

      then:
      with(result) {
         id == grappUser.getId().toString()
         name == grappUser.getName()
         avatar == grappUser.getAvatar()
      }
   }
}
