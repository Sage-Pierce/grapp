package org.codegas.users.service_impl.factory

import org.codegas.users.domain.entity.User
import org.codegas.users.test.builder.UserBuilder
import org.codegas.users.domain.entity.User
import org.codegas.users.test.builder.UserBuilder
import spock.lang.Specification

class UserDtoFactoryTest extends Specification {

   def "A UserDto can be created from a User"() {
      given:
      User user = UserBuilder.build()

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
