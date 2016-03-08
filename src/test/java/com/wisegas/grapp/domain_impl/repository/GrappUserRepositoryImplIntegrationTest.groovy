package com.wisegas.grapp.domain_impl.repository

import com.wisegas.grapp.domain.entity.GrappUser
import com.wisegas.grapp.domain.repository.GrappUserRepository
import com.wisegas.grapp.test.Builders
import com.wisegas.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class GrappUserRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<GrappUser> {

   @Inject
   private GrappUserRepository grappUserRepository

   def "A GrappUser can be found by E-Mail"() {
      given:
      testEntityManager.save(Builders.grappUser().having { it.email = "fun@home.com" })

      when: "We try to find our Test Entity by E-Mail"
      def result = grappUserRepository.findByEmail(emailToLookFor)

      then: "The Test Entity should found based on the E-Mail we looked for"
      shouldBeFound == (result != null)

      where:
      emailToLookFor | shouldBeFound
      "Not an Email" | false
      "fun@home.com" | true
   }
}
