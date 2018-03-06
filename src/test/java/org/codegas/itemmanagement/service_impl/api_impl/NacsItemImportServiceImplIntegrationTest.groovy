package org.codegas.itemmanagement.service_impl.api_impl

import org.codegas.common.test.base.IntegrationTest
import org.codegas.itemmanagement.service.api.NacsItemImportService
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class NacsItemImportServiceImplIntegrationTest extends IntegrationTest {

   @Inject
   private NacsItemImportService nacsItemImportService

   def "Importing a CATEGORY NacsItem should result in a 'general' Item"() {
      when:
      def result = nacsItemImportService.importItems("01-00-00,General Item,")

      then:
      result.size() == 1
      result[0].getName() == "General Item"
      result[0].getLineage().size() == 1
      result[0].getLineage()[0].getName() == "General Item"
   }

   def "Importing a CATEGORY NacsItem with sub-items should result in a 'general' Item with sub-items"() {
      when:
      def result = nacsItemImportService.importItems("01-00-00,General Item,\"Sub-Item 1, Sub-Item 2\"")

      then:
      result.size() == 1
      result[0].getSubItems().size() == 2
      result[0].getSubItems().collect { it.getName() }.containsAll(["Sub-Item 1", "Sub-Item 2"])
   }

   def "Importing a CATEGORY NacsItem with Sub-Category NacsItems should result in a 'general' Item with sub-items"() {
      when:
      def result = nacsItemImportService.importItems("01-00-00,General Item,\n" +
                                                        "01-01-00,Sub-Item 1,")

      then:
      result.size() == 2
      result[0].getSubItems().size() == 1
      result[1].getSubItems().isEmpty()
   }

   def "Importing a CATEGORY NacsItem with Sub-Category NacsItems and sub-items for each results in the correct hierarchy"() {
      when:
      def result = nacsItemImportService.importItems("01-00-00,General Item,Sub-Item 1\n" +
                                                        "01-01-00,Sub-Item 2,Item")

      then:
      result.size() == 2
      result[0].getSubItems().size() == 2
      result[1].getSubItems().size() == 1
   }
}
