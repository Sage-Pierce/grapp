package com.wisegas.test

import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.BeforeTransaction
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.PlatformTransactionManager
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@ContextConfiguration("classpath*:META-INF/Beans.xml")
@TransactionConfiguration(transactionManager = "platformTransactionManager")
abstract class BaseIntegrationTest extends Specification {

   @PersistenceContext
   private EntityManager entityManager
   @Inject
   private PlatformTransactionManager platformTransactionManager
   @Shared
   private Boolean isDBSetup

   @Shared
   IntegrationTestEntityManager testEntityManager

   def setupSpec() {
      isDBSetup = false
   }

   @BeforeTransaction
   def beforeTransaction() {
      if (!isDBSetup) {
         testEntityManager = new IntegrationTestEntityManager(entityManager, platformTransactionManager);
         setupSpecDB()
         isDBSetup = true
      }
   }

   abstract setupSpecDB()
}
