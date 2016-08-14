package com.wisegas.stores.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityPersistenceTest
import com.wisegas.stores.test.builder.NodeItemBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class NodeItemPersistenceTest extends EntityPersistenceTest<NodeItem> {

   @Override
   NodeItem createTestEntity() {
      NodeItemBuilder.build()
   }
}
