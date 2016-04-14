package com.wisegas.user.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.user.domain.entity.User
import com.wisegas.user.domain.repository.UserRepository
import com.wisegas.user.test.builders.UserBuilder
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class UserRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<User> {

   @Inject
   private UserRepository userRepository

   def "A User can be found by E-Mail"() {
      given:
      User user = testEntityManager.save(UserBuilder.user())

      when: "We try to find our Test Entity by E-Mail"
      def result = userRepository.findByEmail(shouldBeFound ? user.getEmail() : "BOGUS EMAIL")

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
