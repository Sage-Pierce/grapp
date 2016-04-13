package com.wisegas.storemanagement.domain.entity

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.storemanagement.domain.value.Item
import com.wisegas.storemanagement.domain.value.NodeType
import com.wisegas.storemanagement.test.builders.LayoutBuilder
import spock.lang.Specification

class StoreLayoutTest extends Specification {

   def "Adding a singleton-Type Node to a Layout which already has one of that Type causes the original one to become whatever the default non-singleton type is"() {
      given:
      Layout grappStoreLayout = LayoutBuilder.layout()

      and:
      Node oldEntrance = grappStoreLayout.addNode(singletonType, new GeoPoint(0, 0))

      when:
      Node newEntrance = grappStoreLayout.addNode(singletonType, new GeoPoint(1, 1))

      then:
      oldEntrance.getType() == NodeType.defaultNonSingleton()
      newEntrance.getType() == singletonType

      where:
      singletonType << NodeType.values().findAll { it.isSingleton() }
   }

   def "Adding an Item to a Node in a Layout removes that Item from any other Nodes in the Layout"() {
      given:
      Layout grappStoreLayout = LayoutBuilder.layout()
      Item item = new Item("CODE", "ITEM")

      and:
      Node node1 = grappStoreLayout.addNode(NodeType.REGULAR, new GeoPoint(0, 0))
      Node node2 = grappStoreLayout.addNode(NodeType.REGULAR, new GeoPoint(1, 1))

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
