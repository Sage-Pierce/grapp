package com.wisegas.grapp.domain.entity

import com.wisegas.grapp.test.Builders
import com.wisegas.persistence.jpa.entity.SimpleEntity
import com.wisegas.test.BaseIntegrationTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared

import java.lang.reflect.ParameterizedType

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

   T createTestEntity() {
      String entityName = ((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName()
      String entityBuilderMethodName = "${Character.toLowerCase(entityName.charAt(0))}${entityName.substring(1)}"
      Builders."$entityBuilderMethodName"()
   }
}
