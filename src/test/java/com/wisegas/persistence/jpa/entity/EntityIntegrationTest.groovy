package com.wisegas.persistence.jpa.entity

import com.wisegas.test.BaseIntegrationTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared

@Transactional
abstract class EntityIntegrationTest<T extends SimpleEntity> extends BaseIntegrationTest {
   @Shared
   T testEntity1
   @Shared
   T testEntity2

   @Override
   def setupSpecDB() {
      testEntity1 = testEntityManager.save(createTestEntity())
      testEntity2 = testEntityManager.save(createTestEntity())
   }

   def "The Object-under-test is found in the persistence mechanism after saving"() {
      expect:
      testEntityManager.getManagedEntity(testEntity1) != null
   }

   def "Two Objects-under-test will have different IDs"() {
      expect:
      !Objects.equals(testEntity1.getId(), testEntity2.getId())
   }

   abstract T createTestEntity()
}
