package org.codegas.itemmanagement.domain_impl.repository

import org.codegas.commons.lang.annotation.Transactional
import org.codegas.service.spock.RepositoryImplPersistenceTest
import org.codegas.itemmanagement.domain.entity.Item
import org.codegas.itemmanagement.domain.repository.ItemRepository
import org.codegas.itemmanagement.domain.value.Code
import org.codegas.itemmanagement.domain.value.CodeType
import org.codegas.itemmanagement.test.builder.ItemBuilder

import javax.inject.Inject

@Transactional
class ItemRepositoryImplPersistenceTest extends RepositoryImplPersistenceTest<Item> {

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
