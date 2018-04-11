package org.codegas.itemmanagement.domain_impl.service

import org.codegas.test.IntegrationTest
import org.codegas.itemmanagement.domain.entity.Item
import org.codegas.itemmanagement.domain.service.ItemImportService
import org.codegas.itemmanagement.domain.value.Code
import org.codegas.itemmanagement.domain.value.CodeType
import org.codegas.itemmanagement.test.builder.ItemBuilder
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class ItemImportServiceImplIntegrationTest extends IntegrationTest {
   private static final String NAME = "Item"

   @Inject
   private ItemImportService itemImportService

   private Code generalCode
   private Item generalItem

   def setup() {
      generalCode = new Code(CodeType.NACS, "00-00-00")
      generalItem = testEntityManager.save(new Item(generalCode, "GENERAL ITEM"))
   }

   def "A General Item can be imported"() {
      given:
      Code code = new Code(CodeType.NACS, "01-00-00")

      when:
      def result = itemImportService.importGeneralItem(code, NAME)

      then:
      result.getId() == code
      result.getName() == NAME
   }

   def "A Sub Item with code can be imported under a General Item"() {
      given:
      Code subCode = new Code(CodeType.NACS, "00-01-00")

      when:
      def result = itemImportService.importSubItem(generalCode, subCode, NAME)

      then:
      generalItem.getSubItems().size() == 1
      generalItem.getSubItems().contains(result)

      and:
      result.getId() == subCode
      result.getName() == NAME
   }

   def "Trying to import an item with the code of one existing Item and the name of another existing Item results in an Exception"() {
      given:
      Item item = ItemBuilder.item()
      Code code = new Code(CodeType.NACS, "01-00-00")

      and:
      testEntityManager.save(item)
      testEntityManager.save(new Item(code, "AN ITEM"))

      when:
      itemImportService.importGeneralItem(code, item.getName())

      then:
      thrown(IllegalStateException)
   }
}
