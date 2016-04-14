package com.wisegas.itemmanagement.domain_impl.repository

import com.wisegas.common.lang.annotation.Transactional
import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.itemmanagement.domain.entity.Item
import com.wisegas.itemmanagement.domain.repository.ItemRepository
import com.wisegas.itemmanagement.domain.value.Code
import com.wisegas.itemmanagement.domain.value.CodeType
import com.wisegas.itemmanagement.test.builders.ItemBuilder

import javax.inject.Inject

@Transactional
class ItemRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<Item> {

   @Inject
   private ItemRepository itemRepository

   def "All 'general' Items can be found"() {
      given:
      Item generalItem = ItemBuilder.item()
      Item subItem = generalItem.addSubItem(new Code(CodeType.MANUAL, "CODE"), "SUB ITEM")
      testEntityManager.save(generalItem)

      when:
      def result = itemRepository.getGeneralItems()

      then:
      result.contains(generalItem)
      !result.contains(subItem)
   }

   def "An Item with a certain name can be found"() {
      given:
      Item item = testEntityManager.save(ItemBuilder.item())

      expect:
      itemRepository.findByName(item.getName()).isPresent()
      !itemRepository.findByName("BOGUS NAME").isPresent()
   }

   def "Items can be found by code"() {
      given:
      Item item = testEntityManager.save(ItemBuilder.item())

      when:
      def result = itemRepository.findByCode(item.getId())

      then:
      result.isPresent()
      result.get().equals(item)
   }

   @Override
   Item createTestEntity() {
      return ItemBuilder.item()
   }
}
