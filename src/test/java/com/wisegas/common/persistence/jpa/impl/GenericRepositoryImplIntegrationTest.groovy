package com.wisegas.common.persistence.jpa.impl

import com.wisegas.common.persistence.jpa.api.GenericRepository
import com.wisegas.common.persistence.jpa.entity.SimpleEntity
import com.wisegas.common.test.IntegrationTest
import com.wisegas.grapp.test.Builders
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject
import java.lang.reflect.ParameterizedType

@Transactional
abstract class GenericRepositoryImplIntegrationTest<T extends SimpleEntity> extends IntegrationTest {

   @Inject
   protected GenericRepository<T> repository

   protected T testEntity

   def setup() {
      testEntity = testEntityManager.save(createTestEntity())
      testEntityManager.flush()
      testEntityManager.clear()
   }

   def "The Entity-under-test can be saved without throwing any Exceptions and will be present in our persistence mechanism"() {
      given: "An Entity"
      def entity = createTestEntity()

      when: "The Entity is saved"
      repository.add(entity)

      and: "We flush within our Transaction"
      testEntityManager.flush()

      then: "No Exception is thrown"
      notThrown(Exception.class)

      and: "It is present in the persistence mechanism"
      testEntityManager.contains(entity)
   }

   def "All instances of the Entity-under-test can be retrieved"() {
      when:
      def result = repository.getAll()

      then:
      result.size() >= 1

      and:
      result.contains(testEntity)
   }

   def "The Entity-under-test can be found by ID"() {
      expect:
      repository.get(testEntity.getId())
   }

   def "The Entity-under-test can be deleted when within a Transaction"() {
      given: "Two Entities"
      def entity1 = createTestEntity()
      def entity2 = createTestEntity()

      and: "We add both"
      repository.add(entity1)
      repository.add(entity2)

      and: "We flush within our transaction"
      testEntityManager.flush()

      when: "We remove the first Entity"
      repository.remove(entity1)

      then: "The first Entity will be gone but the second will remain"
      !testEntityManager.contains(entity1)
      testEntityManager.contains(entity2)
   }

   T createTestEntity() {
      String entityName = ((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName()
      String entityBuilderMethodName = "${Character.toLowerCase(entityName.charAt(0))}${entityName.substring(1)}"
      Builders."$entityBuilderMethodName"()
   }
}
