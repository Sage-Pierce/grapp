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
      superItem.isGeneral()
      !item.isGeneral()
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

   def "An Item can be made a general Item"() {
      given:
      Item generalItem = new Item(SUPER_CODE, "General Item")
      Item item = generalItem.addSubItem(SUB_CODE, "Item")

      when:
      item.makeGeneral()

      then:
      item.isGeneral()

      and:
      generalItem.getSubItems().isEmpty()
   }

   def "An Item from one Super Item can be accepted by another Super Item"() {
      given:
      Item superItem1 = new Item(SUPER_SUPER_CODE, "Super Item 1")
      Item superItem2 = new Item(SUPER_CODE, "Super Item 2")

      and:
      Item subItem = superItem1.addSubItem(SUB_CODE, "Sub Item")

      when:
      superItem2.acceptSubItem(subItem)

      then:
      superItem2.getSubItems().size() == 1
      superItem2.getSubItems()[0] == subItem
      subItem.getSuperItem() == superItem2

      and:
      superItem1.getSubItems().isEmpty()
   }
}
