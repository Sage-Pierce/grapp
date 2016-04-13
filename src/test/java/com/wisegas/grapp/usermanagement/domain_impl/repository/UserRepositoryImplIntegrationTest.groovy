package com.wisegas.grapp.usermanagement.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.grapp.usermanagement.domain.entity.User
import com.wisegas.grapp.usermanagement.domain.repository.UserRepository
import com.wisegas.grapp.usermanagement.test.builders.UserBuilder
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class UserRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<User> {

   @Inject
   private UserRepository grappUserRepository

   def "A GrappUser can be found by E-Mail"() {
      given:
      User grappUser = testEntityManager.save(UserBuilder.user())

      when: "We try to find our Test Entity by E-Mail"
      def result = grappUserRepository.findByEmail(shouldBeFound ? grappUser.getEmail() : "BOGUS EMAIL")

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
