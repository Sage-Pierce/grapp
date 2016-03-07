package com.wisegas.test

import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.BeforeTransaction
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.PlatformTransactionManager
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceContext

@ContextConfiguration("classpath*:META-INF/Beans.xml")
abstract class BaseIntegrationTest extends Specification {

   @Inject
   protected IntegrationTestEntityManager testEntityManager

   @Shared
   private Boolean isDBSetup

   def setupSpec() {
      isDBSetup = false
   }

   @BeforeTransaction
   def beforeTransaction() {
      if (!isDBSetup) {
         setupSpecDB()
         isDBSetup = true
      }
   }

   abstract setupSpecDB()
}
