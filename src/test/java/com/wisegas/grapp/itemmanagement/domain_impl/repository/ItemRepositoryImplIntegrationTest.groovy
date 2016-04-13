package com.wisegas.grapp.itemmanagement.domain_impl.repository

import com.wisegas.common.lang.annotation.Transactional
import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.grapp.itemmanagement.domain.entity.Item
import com.wisegas.grapp.itemmanagement.domain.repository.ItemRepository
import com.wisegas.grapp.itemmanagement.domain.value.Code
import com.wisegas.grapp.itemmanagement.domain.value.CodeType
import com.wisegas.grapp.itemmanagement.test.builders.ItemBuilder

import javax.inject.Inject

@Transactional
class ItemRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<Item> {

   @Inject
   private ItemRepository grappItemRepository

   def "All 'general' GrappItems can be found"() {
      given:
      Item generalItem = ItemBuilder.item()
      Item subItem = generalItem.addSubItem(new Code(CodeType.MANUAL, "CODE"), "SUB ITEM")
      testEntityManager.save(generalItem)

      when:
      def result = grappItemRepository.getGeneralItems()

      then:
      result.contains(generalItem)
      !result.contains(subItem)
   }

   def "A GrappItem with a certain name can be found"() {
      given:
      Item grappItem = testEntityManager.save(ItemBuilder.item())

      expect:
      grappItemRepository.findByName(grappItem.getName()).isPresent()
      !grappItemRepository.findByName("BOGUS NAME").isPresent()
   }

   def "GrappItems can be found by code"() {
      given:
      Item grappItem = testEntityManager.save(ItemBuilder.item())

      when:
      def result = grappItemRepository.findByCode(grappItem.getId())

      then:
      result.isPresent()
      result.get().equals(grappItem)
   }

   @Override
   Item createTestEntity() {
      return ItemBuilder.item()
   }
}
