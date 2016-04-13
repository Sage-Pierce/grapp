package com.wisegas.storemanagement.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.storemanagement.domain.entity.NodeItem
import com.wisegas.storemanagement.test.builders.NodeItemBuilder

class StoreNodeItemRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<NodeItem> {

   @Override
   NodeItem createTestEntity() {
      return NodeItemBuilder.nodeItem()
   }
}
