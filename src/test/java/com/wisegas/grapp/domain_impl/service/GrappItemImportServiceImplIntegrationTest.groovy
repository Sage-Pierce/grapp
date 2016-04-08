package com.wisegas.grapp.domain_impl.service

import com.wisegas.common.test.IntegrationTest
import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem
import com.wisegas.grapp.itemmanagement.domain.service.GrappItemImportService
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCodeType
import com.wisegas.grapp.test.Builders
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
      generalItem = testEntityManager.save(createItemWithCode(generalCode))
   }

   def "A General Item can be imported"() {
      given:
      GrappItemCode code = new GrappItemCode(GrappItemCodeType.NACS, "00-00-00")

      when:
      def result = grappItemImportService.importGeneralItem(code, NAME)

      then:
      result.getName() == NAME
      result.getCodes().size() == 1
      result.getCodes().contains(code)
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
      result.getName() == NAME
      result.getCodes().size() == 1
      result.getCodes().contains(subCode)
   }

   def "A Sub Item can be imported under a General Item"() {
      when:
      def result = grappItemImportService.importSubItem(generalCode, NAME)

      then:
      generalItem.getSubItems().size() == 1
      generalItem.getSubItems().contains(result)

      and:
      result.getName() == NAME
      result.getCodes().isEmpty()
   }

   def "Importing an Item with a code applies the code to an Item that already has that Item's name"() {
      given:
      GrappItem grappItem = testEntityManager.save(Builders.grappItem())

      and:
      GrappItemCode code = new GrappItemCode(GrappItemCodeType.NACS, "01-00-00")

      when:
      def result = grappItemImportService.importGeneralItem(code, grappItem.getName())

      then:
      result == grappItem
      result.getCodes().size() == 1
      result.getCodes().contains(code)
   }

   def "Importing an Item with a code applies the name to an Item that already has that Item's code"() {
      given:
      GrappItemCode code = new GrappItemCode(GrappItemCodeType.NACS, "01-00-00")

      and:
      GrappItem grappItem = testEntityManager.save(createItemWithCode(code))

      and:
      String name = "NEW NAME"

      when:
      def result = grappItemImportService.importGeneralItem(code, name)

      then:
      result == grappItem
      result.getName() == name
   }

   def "Trying to import an item with the code of one existing Item and the name of another existing Item results in an Exception"() {
      given:
      String name = "Grapp Item"
      GrappItemCode code = new GrappItemCode(GrappItemCodeType.NACS, "01-00-00")

      and:
      testEntityManager.save(Builders.grappItem().having { it.name = name })
      testEntityManager.save(createItemWithCode(code))

      when:
      grappItemImportService.importGeneralItem(code, name)

      then:
      thrown(IllegalStateException)
   }

   def "Trying to import a Sub-Item when the Super-Item cannot be found results in an Exception"() {
      when:
      grappItemImportService.importSubItem(new GrappItemCode(GrappItemCodeType.NACS, "01-00-00"), "NAME")

      then:
      thrown(IllegalStateException)
   }

   private static GrappItem createItemWithCode(GrappItemCode code) {
      GrappItem item = Builders.grappItem()
      item.addCode(code)
      return item
   }
}
