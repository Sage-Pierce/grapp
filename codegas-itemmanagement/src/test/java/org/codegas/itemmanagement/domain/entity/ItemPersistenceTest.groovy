package org.codegas.itemmanagement.domain.entity

import org.codegas.service.spock.EntityPersistenceTest
import org.codegas.itemmanagement.test.builder.ItemBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class ItemPersistenceTest extends EntityPersistenceTest<Item> {

   @Override
   Item createTestEntity() {
      ItemBuilder.item()
   }
}