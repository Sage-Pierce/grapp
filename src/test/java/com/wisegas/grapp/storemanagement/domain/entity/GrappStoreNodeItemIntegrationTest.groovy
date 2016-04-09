package com.wisegas.grapp.storemanagement.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.grapp.storemanagement.test.builders.GrappStoreNodeItemBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappStoreNodeItemIntegrationTest extends EntityIntegrationTest<GrappStoreNodeItem> {

   @Override
   GrappStoreNodeItem createTestEntity() {
      GrappStoreNodeItemBuilder.grappStoreNodeItem()
   }
}
