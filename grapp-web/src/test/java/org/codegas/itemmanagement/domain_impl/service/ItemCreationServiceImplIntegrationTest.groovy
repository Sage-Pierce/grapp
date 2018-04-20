package org.codegas.itemmanagement.domain_impl.service

import org.codegas.commons.lang.annotation.Transactional
import org.codegas.test.spock.IntegrationTest
import org.codegas.itemmanagement.domain.entity.Item
import org.codegas.itemmanagement.domain.service.ItemCreationService
import org.codegas.itemmanagement.domain.value.Code
import org.codegas.itemmanagement.domain.value.CodeType
import org.codegas.itemmanagement.test.builder.ItemBuilder

import javax.inject.Inject

@Transactional
class ItemCreationServiceImplIntegrationTest extends IntegrationTest {

   @Inject
   private ItemCreationService itemCreationService

   def "An Item cannot be created or modified to a certain name if an Item of that name already exists"() {
      given:
      Item existingItem1 = testEntityManager.save(ItemBuilder.item())
      Item existingItem2 = testEntityManager.save(ItemBuilder.item())

      when:
      itemCreationService.createGeneralItem(new Code(CodeType.MANUAL, "CODE 1"), existingItem1.getName())

      then:
      thrown(Exception)

      when:
      itemCreationService.createSubItem(existingItem1.getId(), new Code(CodeType.MANUAL, "CODE 2"), existingItem2.getName())

      then:
      thrown(Exception)
   }
}
