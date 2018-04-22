package org.codegas.itemmanagement.domain_impl.service

import org.codegas.commons.domain.exception.DomainEntityConflictException
import org.codegas.test.spock.IntegrationTest
import org.codegas.itemmanagement.domain.entity.Item
import org.codegas.itemmanagement.domain.service.ItemUpdateService
import org.codegas.itemmanagement.test.builder.ItemBuilder
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class ItemUpdateServiceImplIntegrationTest extends IntegrationTest {

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
      thrown(DomainEntityConflictException)
   }
}
