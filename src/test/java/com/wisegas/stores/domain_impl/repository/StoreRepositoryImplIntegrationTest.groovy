package com.wisegas.stores.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.stores.domain.entity.Store
import com.wisegas.stores.test.builders.StoreBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class StoreRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<Store> {

   @Override
   Store createTestEntity() {
      StoreBuilder.store()
   }
}
