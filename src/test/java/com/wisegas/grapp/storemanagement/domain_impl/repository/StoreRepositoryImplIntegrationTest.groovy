package com.wisegas.grapp.storemanagement.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.grapp.storemanagement.domain.entity.Store
import com.wisegas.grapp.storemanagement.test.builders.GrappStoreBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class StoreRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<Store> {

   @Override
   Store createTestEntity() {
      GrappStoreBuilder.grappStore()
   }
}
