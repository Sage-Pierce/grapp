package com.wisegas.grapp.storemanagement.domain.entity

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.grapp.storemanagement.domain.value.GrappStoreNodeType
import com.wisegas.grapp.storemanagement.domain.value.Item
import com.wisegas.grapp.storemanagement.test.builders.GrappStoreLayoutBuilder
import spock.lang.Specification

class GrappStoreLayoutTest extends Specification {

   def "Adding a singleton-Type Node to a Layout which already has one of that Type causes the original one to become whatever the default non-singleton type is"() {
      given:
      GrappStoreLayout grappStoreLayout = GrappStoreLayoutBuilder.grappStoreLayout()

      and:
      GrappStoreNode oldEntrance = grappStoreLayout.addNode(singletonType, new GeoPoint(0, 0))

      when:
      GrappStoreNode newEntrance = grappStoreLayout.addNode(singletonType, new GeoPoint(1, 1))

      then:
      oldEntrance.getType() == GrappStoreNodeType.defaultNonSingleton()
      newEntrance.getType() == singletonType

      where:
      singletonType << GrappStoreNodeType.values().findAll { it.isSingleton() }
   }

   def "Adding an Item to a Node in a Layout removes that Item from any other Nodes in the Layout"() {
      given:
      GrappStoreLayout grappStoreLayout = GrappStoreLayoutBuilder.grappStoreLayout()
      Item item = new Item("CODE", "ITEM")

      and:
      GrappStoreNode node1 = grappStoreLayout.addNode(GrappStoreNodeType.REGULAR, new GeoPoint(0, 0))
      GrappStoreNode node2 = grappStoreLayout.addNode(GrappStoreNodeType.REGULAR, new GeoPoint(1, 1))

      and:
      grappStoreLayout.addNodeItem(node1.getId(), item)

      when:
      grappStoreLayout.addNodeItem(node2.getId(), item)

      then:
      !node1.containsItem(item)

      and:
      node2.containsItem(item)
   }
}
