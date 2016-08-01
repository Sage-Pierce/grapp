package com.wisegas.users.domain_impl.repository

import com.wisegas.common.lang.value.Email
import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplPersistenceTest
import com.wisegas.users.domain.entity.User
import com.wisegas.users.domain.repository.UserRepository
import com.wisegas.users.test.builder.UserBuilder
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class UserRepositoryImplPersistenceTest extends GenericRepositoryImplPersistenceTest<User> {

   @Inject
   private UserRepository userRepository

   def "A User can be found by E-Mail"() {
      given:
      User user = testEntityManager.save(UserBuilder.user())

      when: "We try to find our Test Entity by E-Mail"
      def result = userRepository.findByEmail(shouldBeFound ? user.getId() : Email.fromString("BOGUS@EMAIL.COM"))

      then: "The Test Entity should found based on the E-Mail we looked for"
      shouldBeFound == result.isPresent()

      where:
      shouldBeFound << [true, false]
   }

   @Override
   User createTestEntity() {
      UserBuilder.user()
   }
}
