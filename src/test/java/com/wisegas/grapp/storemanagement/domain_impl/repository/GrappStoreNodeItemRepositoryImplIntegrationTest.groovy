package com.wisegas.grapp.storemanagement.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNodeItem
import com.wisegas.grapp.storemanagement.test.builders.GrappStoreNodeItemBuilder

class GrappStoreNodeItemRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<GrappStoreNodeItem> {

   @Override
   GrappStoreNodeItem createTestEntity() {
      return GrappStoreNodeItemBuilder.grappStoreNodeItem()
   }
}
