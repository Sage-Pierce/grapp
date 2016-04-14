package com.wisegas.storemanagement.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.storemanagement.test.builders.NodeItemBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class NodeItemIntegrationTest extends EntityIntegrationTest<NodeItem> {

   @Override
   NodeItem createTestEntity() {
      NodeItemBuilder.nodeItem()
   }
}
