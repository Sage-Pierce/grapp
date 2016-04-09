package com.wisegas.grapp.itemmanagement.domain.entity

import spock.lang.Specification

class GrappItemTest extends Specification {

   def "A SubItem can be added to another Item"() {
      given:
      GrappItem superItem = new GrappItem("Super Item")

      when:
      def result = superItem.addSubItem("Sub Item")

      then:
      superItem.getSubItems().size() == 1

      and:
      result.getSuperItem() == superItem
   }

   def "A GrappItem is a 'general' Item if it has no super Item"() {
      given:
      GrappItem superItem = new GrappItem("Super Item")
      GrappItem item = superItem.addSubItem("Item")

      expect:
      superItem.isGeneralItem()
      !item.isGeneralItem()
   }

   def "The Hierarchy of a GrappItem can be determined"() {
      given:
      GrappItem superSuperItem = new GrappItem("Super Super Item")
      GrappItem superItem = superSuperItem.addSubItem("Super Item")
      GrappItem item = superItem.addSubItem("Item")

      when:
      def result = item.getHierarchy()

      then:
      result.size() == 3
      result[0] == superSuperItem
      result[1] == superItem
      result[2] == item
   }
}
