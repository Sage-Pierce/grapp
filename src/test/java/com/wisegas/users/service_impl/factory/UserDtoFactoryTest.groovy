package com.wisegas.users.service_impl.factory

import com.wisegas.users.domain.entity.User
import com.wisegas.users.test.builders.UserBuilder
import spock.lang.Specification

class UserDtoFactoryTest extends Specification {

   def "A UserDto can be created from a User"() {
      given:
      User user = UserBuilder.user()

      when:
      def result = UserDtoFactory.createDto(user)

      then:
      with(result) {
         email == user.getEmail().toString()
         name == user.getName()
         avatar == user.getAvatar()
      }
   }
}
