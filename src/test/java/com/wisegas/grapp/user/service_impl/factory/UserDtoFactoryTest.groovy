package com.wisegas.grapp.user.service_impl.factory

import com.wisegas.grapp.user.domain.entity.User
import spock.lang.Specification

class UserDtoFactoryTest extends Specification {

   def "A GrappUserResource can be created from a GrappUser"() {
      given:
      User grappUser = new User("Test_Email", "Test", "Test_Avatar")

      when:
      def result = UserDtoFactory.createDto(grappUser)

      then:
      with(result) {
         id == grappUser.getId().toString()
         name == grappUser.getName()
         avatar == grappUser.getAvatar()
      }
   }
}
