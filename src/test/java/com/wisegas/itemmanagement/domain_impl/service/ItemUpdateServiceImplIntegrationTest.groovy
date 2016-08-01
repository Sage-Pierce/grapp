package com.wisegas.itemmanagement.domain_impl.service

import com.wisegas.common.domain.exception.EntityConflictException
import com.wisegas.common.test.base.PersistenceTest
import com.wisegas.itemmanagement.domain.entity.Item
import com.wisegas.itemmanagement.domain.service.ItemUpdateService
import com.wisegas.itemmanagement.test.builder.ItemBuilder
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class ItemUpdateServiceImplIntegrationTest extends PersistenceTest {

   @Inject
   private ItemUpdateService itemUpdateService

   def "An Item's name can be changed"() {
      given:
      Item existingItem = testEntityManager.save(ItemBuilder.item())

      when:
      itemUpdateService.changeItemName(existingItem, "NEW NAME")

      then:
      testEntityManager.getManagedEntity(existingItem).getName() == "NEW NAME"
   }

   def "We'll get an Exception if we try to change an Item's name to one that already exists"() {
      given:
      Item existingItem1 = testEntityManager.save(ItemBuilder.item())
      Item existingItem2 = testEntityManager.save(ItemBuilder.item())

      when:
      itemUpdateService.changeItemName(existingItem2, existingItem1.getName())

      then:
      thrown(EntityConflictException)
   }
}
