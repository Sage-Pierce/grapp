package com.wisegas.grapp.itemmanagement.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.grapp.itemmanagement.test.builders.ItemBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class ItemIntegrationTest extends EntityIntegrationTest<Item> {

   @Override
   Item createTestEntity() {
      ItemBuilder.grappItem()
   }
}