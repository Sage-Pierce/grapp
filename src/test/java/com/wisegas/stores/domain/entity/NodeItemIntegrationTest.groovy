package com.wisegas.stores.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.stores.test.builders.NodeItemBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class NodeItemIntegrationTest extends EntityIntegrationTest<NodeItem> {

   @Override
   NodeItem createTestEntity() {
      NodeItemBuilder.nodeItem()
   }
}
