package com.wisegas.user.service_impl.factory

import com.wisegas.user.domain.entity.User
import com.wisegas.user.test.builders.UserBuilder
import spock.lang.Specification

class UserDtoFactoryTest extends Specification {

   def "A UserResource can be created from a User"() {
      given:
      User user = UserBuilder.user()

      when:
      def result = UserDtoFactory.createDto(user)

      then:
      with(result) {
         id == user.getId().toString()
         name == user.getName()
         avatar == user.getAvatar()
      }
   }
}
