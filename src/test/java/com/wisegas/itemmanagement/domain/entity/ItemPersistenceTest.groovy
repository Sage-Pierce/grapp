package com.wisegas.itemmanagement.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityPersistenceTest
import com.wisegas.itemmanagement.test.builders.ItemBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class ItemPersistenceTest extends EntityPersistenceTest<Item> {

   @Override
   Item createTestEntity() {
      ItemBuilder.item()
   }
}