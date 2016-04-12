package com.wisegas.grapp.itemmanagement.domain.entity

import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCodeType
import spock.lang.Specification

class GrappItemTest extends Specification {
   private static final SUPER_SUPER_CODE = new GrappItemCode(GrappItemCodeType.MANUAL, "ID1")
   private static final SUPER_CODE = new GrappItemCode(GrappItemCodeType.MANUAL, "ID2")
   private static final SUB_CODE = new GrappItemCode(GrappItemCodeType.MANUAL, "ID3")

   def "A SubItem can be added to another Item"() {
      given:
      GrappItem superItem = new GrappItem(SUPER_CODE, "Super Item")

      when:
      def result = superItem.addSubItem(SUB_CODE, "Sub Item")

      then:
      superItem.getSubItems().size() == 1

      and:
      result.getSuperItem() == superItem
   }

   def "A GrappItem is a 'general' Item if it has no super Item"() {
      given:
      GrappItem superItem = new GrappItem(SUPER_CODE, "Super Item")
      GrappItem item = superItem.addSubItem(SUB_CODE, "Item")

      expect:
      superItem.isGeneralItem()
      !item.isGeneralItem()
   }

   def "The Hierarchy of a GrappItem can be determined"() {
      given:
      GrappItem superSuperItem = new GrappItem(SUPER_SUPER_CODE, "Super Super Item")
      GrappItem superItem = superSuperItem.addSubItem(SUPER_CODE, "Super Item")
      GrappItem item = superItem.addSubItem(SUB_CODE, "Item")

      when:
      def result = item.getHierarchy()

      then:
      result.size() == 3
      result[0] == superSuperItem
      result[1] == superItem
      result[2] == item
   }
}
