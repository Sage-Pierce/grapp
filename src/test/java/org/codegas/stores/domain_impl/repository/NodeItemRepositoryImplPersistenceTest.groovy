package org.codegas.stores.domain_impl.repository

import org.codegas.commons.persistence.jpa.impl.GenericRepositoryImplPersistenceTest
import org.codegas.stores.test.builder.NodeItemBuilder
import org.codegas.stores.domain.entity.NodeItem

class NodeItemRepositoryImplPersistenceTest extends GenericRepositoryImplPersistenceTest<NodeItem> {

   @Override
   NodeItem createTestEntity() {
      return NodeItemBuilder.build()
   }
}
