package com.wisegas.stores.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.stores.domain.entity.NodeItem
import com.wisegas.stores.test.builders.NodeItemBuilder

class NodeItemRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<NodeItem> {

   @Override
   NodeItem createTestEntity() {
      return NodeItemBuilder.nodeItem()
   }
}
