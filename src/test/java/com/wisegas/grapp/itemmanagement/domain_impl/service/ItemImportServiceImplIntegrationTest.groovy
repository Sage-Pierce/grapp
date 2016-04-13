package com.wisegas.grapp.itemmanagement.domain_impl.service

import com.wisegas.common.test.IntegrationTest
import com.wisegas.grapp.itemmanagement.domain.entity.Item
import com.wisegas.grapp.itemmanagement.domain.service.ItemImportService
import com.wisegas.grapp.itemmanagement.domain.value.Code
import com.wisegas.grapp.itemmanagement.domain.value.CodeType
import com.wisegas.grapp.itemmanagement.test.builders.ItemBuilder
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class ItemImportServiceImplIntegrationTest extends IntegrationTest {
   private static final String NAME = "Grapp Item"

   @Inject
   private ItemImportService grappItemImportService

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
      def result = grappItemImportService.importGeneralItem(code, NAME)

      then:
      result.getId() == code
      result.getName() == NAME
   }

   def "A Sub Item with code can be imported under a General Item"() {
      given:
      Code subCode = new Code(CodeType.NACS, "00-01-00")

      when:
      def result = grappItemImportService.importSubItem(generalCode, subCode, NAME)

      then:
      generalItem.getSubItems().size() == 1
      generalItem.getSubItems().contains(result)

      and:
      result.getId() == subCode
      result.getName() == NAME
   }

   def "Trying to import an item with the code of one existing Item and the name of another existing Item results in an Exception"() {
      given:
      Item grappItem = ItemBuilder.grappItem()
      Code code = new Code(CodeType.NACS, "01-00-00")

      and:
      testEntityManager.save(grappItem)
      testEntityManager.save(new Item(code, "AN ITEM"))

      when:
      grappItemImportService.importGeneralItem(code, grappItem.getName())

      then:
      thrown(IllegalStateException)
   }
}
