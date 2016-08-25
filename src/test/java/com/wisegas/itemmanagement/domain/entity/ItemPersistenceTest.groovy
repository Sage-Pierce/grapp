package com.wisegas.itemmanagement.domain.entity

import com.wisegas.common.test.base.EntityPersistenceTest
import com.wisegas.itemmanagement.test.builder.ItemBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class ItemPersistenceTest extends EntityPersistenceTest<Item> {

   @Override
   Item createTestEntity() {
      ItemBuilder.item()
   }
}