package org.codegas.stores.domain.entity

import org.codegas.commons.test.EntityPersistenceTest
import org.codegas.stores.test.builder.NodeItemBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class NodeItemPersistenceTest extends EntityPersistenceTest<NodeItem> {

   @Override
   NodeItem createTestEntity() {
      NodeItemBuilder.build()
   }
}
