package com.wisegas.persistence.jpa.impl

import com.wisegas.grapp.test.Builders
import com.wisegas.persistence.jpa.entity.SimpleEntity
import com.wisegas.persistence.jpa.api.GenericRepository
import com.wisegas.test.BaseIntegrationTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared

import javax.inject.Inject
import java.lang.reflect.ParameterizedType

@Transactional
abstract class GenericRepositoryImplIntegrationTest<T extends SimpleEntity> extends BaseIntegrationTest {

   @Inject
   protected GenericRepository<T> repository

   @Shared
   T testEntity

   @Override
   def setupSpecDB() {
      testEntity = testEntityManager.save(createTestEntity())
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
      repository.findByID(testEntity.getId())
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
