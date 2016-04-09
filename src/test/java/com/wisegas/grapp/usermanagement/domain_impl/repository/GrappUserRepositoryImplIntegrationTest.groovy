package com.wisegas.grapp.usermanagement.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.grapp.usermanagement.domain.entity.GrappUser
import com.wisegas.grapp.usermanagement.domain.repository.GrappUserRepository
import com.wisegas.grapp.usermanagement.test.builders.GrappUserBuilder
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class GrappUserRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<GrappUser> {

   @Inject
   private GrappUserRepository grappUserRepository

   def "A GrappUser can be found by E-Mail"() {
      given:
      GrappUser grappUser = testEntityManager.save(GrappUserBuilder.grappUser())

      when: "We try to find our Test Entity by E-Mail"
      def result = grappUserRepository.findByEmail(shouldBeFound ? grappUser.getEmail() : "BOGUS EMAIL")

      then: "The Test Entity should found based on the E-Mail we looked for"
      shouldBeFound == result.isPresent()

      where:
      shouldBeFound << [true, false]
   }

   @Override
   GrappUser createTestEntity() {
      GrappUserBuilder.grappUser()
   }
}
