package com.wisegas.stores.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplPersistenceTest
import com.wisegas.stores.domain.entity.Store
import com.wisegas.stores.test.builder.StoreBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class StoreRepositoryImplPersistenceTest extends GenericRepositoryImplPersistenceTest<Store> {

   @Override
   Store createTestEntity() {
      StoreBuilder.build()
   }
}
