package org.codegas.stores.domain_impl.repository

import org.codegas.service.spock.RepositoryImplPersistenceTest
import org.codegas.stores.domain.entity.Store
import org.codegas.stores.test.builder.StoreBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class StoreRepositoryImplPersistenceTest extends RepositoryImplPersistenceTest<Store> {

   @Override
   Store createTestEntity() {
      StoreBuilder.build()
   }
}
