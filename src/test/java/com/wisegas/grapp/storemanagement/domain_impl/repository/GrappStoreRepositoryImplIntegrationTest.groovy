package com.wisegas.grapp.storemanagement.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.grapp.storemanagement.domain.entity.GrappStore
import com.wisegas.grapp.storemanagement.test.builders.GrappStoreBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappStoreRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<GrappStore> {

   @Override
   GrappStore createTestEntity() {
      GrappStoreBuilder.grappStore()
   }
}
