package com.wisegas.itemmanagement.domain_impl.service

import com.wisegas.common.lang.annotation.Transactional
import com.wisegas.common.test.IntegrationTest
import com.wisegas.itemmanagement.domain.entity.Item
import com.wisegas.itemmanagement.domain.value.Code
import com.wisegas.itemmanagement.domain.value.CodeType
import com.wisegas.itemmanagement.test.builders.ItemBuilder

import javax.inject.Inject

@Transactional
class ItemCrudServiceImplIntegrationTest extends IntegrationTest {

   @Inject
   private ItemCrudServiceImpl itemCrudService

   def "An Item cannot be created or modified to a certain name if an Item of that name already exists"() {
      given:
      Item existingItem1 = testEntityManager.save(ItemBuilder.item())
      Item existingItem2 = testEntityManager.save(ItemBuilder.item())

      when:
      itemCrudService.createGeneralItem(new Code(CodeType.MANUAL, "CODE 1"), existingItem1.getName())

      then:
      thrown(Exception)

      when:
      itemCrudService.createSubItem(existingItem1.getId(), new Code(CodeType.MANUAL, "CODE 2"), existingItem2.getName())

      then:
      thrown(Exception)
   }
}
