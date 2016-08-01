package com.wisegas.stores.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplPersistenceTest
import com.wisegas.stores.domain.entity.NodeItem
import com.wisegas.stores.test.builder.NodeItemBuilder

class NodeItemRepositoryImplPersistenceTest extends GenericRepositoryImplPersistenceTest<NodeItem> {

   @Override
   NodeItem createTestEntity() {
      return NodeItemBuilder.nodeItem()
   }
}
