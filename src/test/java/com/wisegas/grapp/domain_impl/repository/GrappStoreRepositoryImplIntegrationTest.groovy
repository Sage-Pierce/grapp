package com.wisegas.grapp.domain_impl.repository

import com.wisegas.grapp.domain.entity.GrappStore
import com.wisegas.grapp.domain.repository.GrappStoreRepository
import com.wisegas.grapp.domain.value.GrappUserID
import com.wisegas.grapp.test.Builders
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared

import javax.inject.Inject

@Transactional
class GrappStoreRepositoryImplIntegrationTest extends GenericEntityRepositoryImplIntegrationTest<GrappStore> {

   @Shared
   GrappUserID grappStoreOwnerID

   @Inject
   GrappStoreRepository grappStoreRepository

   @Override
   def setupSpecDB() {
      super.setupSpecDB()
      def grappStore = testEntityManager.save(Builders.grappStore())
      grappStoreOwnerID = grappStore.getOwner().getId()
   }

   def "A List of GrappStores can be found by owner"() {
      when:
      def result = grappStoreRepository.findAllForOwner(grappStoreOwnerID)

      then:
      result.size() == 1
      result.get(0).getOwner().getId() == grappStoreOwnerID
   }
}
