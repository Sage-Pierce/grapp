package org.codegas.stores.domain_impl.repository

import org.codegas.service.spock.RepositoryImplPersistenceTest
import org.codegas.stores.test.builder.NodeItemBuilder
import org.codegas.stores.domain.entity.NodeItem

class NodeItemRepositoryImplPersistenceTest extends RepositoryImplPersistenceTest<NodeItem> {

   @Override
   NodeItem createTestEntity() {
      return NodeItemBuilder.build()
   }
}
