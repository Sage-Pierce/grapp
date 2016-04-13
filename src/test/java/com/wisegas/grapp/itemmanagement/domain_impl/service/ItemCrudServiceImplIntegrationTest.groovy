package com.wisegas.grapp.itemmanagement.domain_impl.service

import com.wisegas.common.lang.annotation.Transactional
import com.wisegas.common.test.IntegrationTest
import com.wisegas.grapp.itemmanagement.domain.entity.Item
import com.wisegas.grapp.itemmanagement.domain.value.Code
import com.wisegas.grapp.itemmanagement.domain.value.CodeType
import com.wisegas.grapp.itemmanagement.test.builders.ItemBuilder

import javax.inject.Inject

@Transactional
class ItemCrudServiceImplIntegrationTest extends IntegrationTest {

   @Inject
   private ItemCrudServiceImpl grappItemCrudService

   def "A GrappItem cannot be created or modified to a certain name if an Item of that name already exists"() {
      given:
      Item existingItem1 = testEntityManager.save(ItemBuilder.item())
      Item existingItem2 = testEntityManager.save(ItemBuilder.item())

      when:
      grappItemCrudService.createGeneralItem(new Code(CodeType.MANUAL, "CODE 1"), existingItem1.getName())

      then:
      thrown(Exception)

      when:
      grappItemCrudService.createSubItem(existingItem1.getId(), new Code(CodeType.MANUAL, "CODE 2"), existingItem2.getName())

      then:
      thrown(Exception)

      when:
      grappItemCrudService.updateName(existingItem2.getId(), existingItem1.getName())

      then:
      thrown(Exception)
   }
}
