package com.wisegas.grapp.itemmanagement.domain_impl.service

import com.wisegas.common.test.IntegrationTest
import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem
import com.wisegas.grapp.itemmanagement.domain.service.GrappItemImportService
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCodeType
import com.wisegas.grapp.itemmanagement.test.builders.GrappItemBuilder
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class GrappItemImportServiceImplIntegrationTest extends IntegrationTest {
   private static final String NAME = "Grapp Item"

   @Inject
   private GrappItemImportService grappItemImportService

   private GrappItemCode generalCode
   private GrappItem generalItem

   def setup() {
      generalCode = new GrappItemCode(GrappItemCodeType.NACS, "00-00-00")
      generalItem = testEntityManager.save(new GrappItem(generalCode, "GENERAL ITEM"))
   }

   def "A General Item can be imported"() {
      given:
      GrappItemCode code = new GrappItemCode(GrappItemCodeType.NACS, "01-00-00")

      when:
      def result = grappItemImportService.importGeneralItem(code, NAME)

      then:
      result.getId() == code
      result.getName() == NAME
   }

   def "A Sub Item with code can be imported under a General Item"() {
      given:
      GrappItemCode subCode = new GrappItemCode(GrappItemCodeType.NACS, "00-01-00")

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
      GrappItem grappItem = GrappItemBuilder.grappItem()
      GrappItemCode code = new GrappItemCode(GrappItemCodeType.NACS, "01-00-00")

      and:
      testEntityManager.save(grappItem)
      testEntityManager.save(new GrappItem(code, "AN ITEM"))

      when:
      grappItemImportService.importGeneralItem(code, grappItem.getName())

      then:
      thrown(IllegalStateException)
   }
}
