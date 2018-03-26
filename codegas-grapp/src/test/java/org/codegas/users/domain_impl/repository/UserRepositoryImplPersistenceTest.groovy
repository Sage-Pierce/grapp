package org.codegas.users.domain_impl.repository

import org.codegas.commons.lang.value.Email
import org.codegas.test.GenericRepositoryImplPersistenceTest
import org.codegas.users.domain.entity.User
import org.codegas.users.domain.repository.UserRepository
import org.codegas.users.test.builder.UserBuilder
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class UserRepositoryImplPersistenceTest extends GenericRepositoryImplPersistenceTest<User> {

   @Inject
   private UserRepository userRepository

   def "A User can be found by E-Mail"() {
      given:
      User user = testEntityManager.save(UserBuilder.build())

      when: "We try to find our Test Entity by E-Mail"
      def result = userRepository.findByEmail(shouldBeFound ? user.getId() : Email.fromString("BOGUS@EMAIL.COM"))

      then: "The Test Entity should found based on the E-Mail we looked for"
      shouldBeFound == result.isPresent()

      where:
      shouldBeFound << [true, false]
   }

   @Override
   User createTestEntity() {
      UserBuilder.build()
   }
}
