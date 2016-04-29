package com.wisegas.itemmanagement.domain.entity

import com.wisegas.itemmanagement.domain.value.Code
import com.wisegas.itemmanagement.domain.value.CodeType
import spock.lang.Specification

class ItemTest extends Specification {
   private static final SUPER_SUPER_CODE = new Code(CodeType.MANUAL, "ID1")
   private static final SUPER_CODE = new Code(CodeType.MANUAL, "ID2")
   private static final SUB_CODE = new Code(CodeType.MANUAL, "ID3")

   def "A SubItem can be added to another Item"() {
      given:
      Item superItem = new Item(SUPER_CODE, "Super Item")

      when:
      def result = superItem.addSubItem(SUB_CODE, "Sub Item")

      then:
      superItem.getSubItems().size() == 1

      and:
      result.getSuperItem() == superItem
   }

   def "An Item is a 'general' Item if it has no super Item"() {
      given:
      Item superItem = new Item(SUPER_CODE, "Super Item")
      Item item = superItem.addSubItem(SUB_CODE, "Item")

      expect:
      superItem.isGeneralItem()
      !item.isGeneralItem()
   }

   def "The Lineage of an Item can be determined"() {
      given:
      Item superSuperItem = new Item(SUPER_SUPER_CODE, "Super Super Item")
      Item superItem = superSuperItem.addSubItem(SUPER_CODE, "Super Item")
      Item item = superItem.addSubItem(SUB_CODE, "Item")

      when:
      def result = item.getLineage()

      then:
      result.size() == 3
      result[0] == item
      result[1] == superItem
      result[2] == superSuperItem
   }
}
