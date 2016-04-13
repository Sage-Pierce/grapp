package com.wisegas.grapp.storemanagement.service_impl.api_impl

import com.wisegas.common.lang.value.CodeName
import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.test.ApplicationServiceTest
import com.wisegas.grapp.storemanagement.domain.entity.Layout
import com.wisegas.grapp.storemanagement.domain.entity.Node
import com.wisegas.grapp.storemanagement.domain.repository.LayoutRepository
import com.wisegas.grapp.storemanagement.domain.value.Item
import com.wisegas.grapp.storemanagement.domain.value.NodeType
import com.wisegas.grapp.storemanagement.test.builders.LayoutBuilder

class StoreLayoutServiceImplTest extends ApplicationServiceTest {

   LayoutServiceImpl grappStoreLayoutService
   LayoutRepository grappStoreLayoutRepository

   def setup() {
      grappStoreLayoutRepository = Mock(LayoutRepository)
      grappStoreLayoutService = new LayoutServiceImpl(grappStoreLayoutRepository)
   }

   def "The result of adding a Node through the Service updates the node and notifies of affected Nodes"() {
      given:
      Layout layout = LayoutBuilder.layout()
      Node oldEntrance = layout.addNode(NodeType.ENTRANCE, new GeoPoint(0, 0))
      Node regularNode = layout.addNode(NodeType.REGULAR, new GeoPoint(0, 1))

      and:
      grappStoreLayoutRepository.get(layout.getId()) >> layout

      when:
      def result = grappStoreLayoutService.addNode(layout.getId().toString(), NodeType.ENTRANCE.name(), new GeoPoint(1, 1))

      then:
      result.getTarget().getType() == NodeType.ENTRANCE.name()
      result.getTarget().getLocation() == new GeoPoint(1, 1)
      result.getAffectedNodes().size() == 2
      result.getAffectedNodes().collect { it.getId() }.contains(oldEntrance.getId().toString())
      !result.getAffectedNodes().collect { it.getId() }.contains(regularNode.getId().toString())
   }

   def "The result of adding an Item to a Node through the Service updates the Node and notifies of affected Nodes"() {
      given:
      CodeName item = new CodeName("CODE", "ITEM")
      Layout layout = LayoutBuilder.layout()

      and:
      grappStoreLayoutRepository.get(layout.getId()) >> layout

      and:
      Node node1 = layout.addNode(NodeType.REGULAR, new GeoPoint(0, 0))
      Node node2 = layout.addNode(NodeType.REGULAR, new GeoPoint(0, 0))

      and:
      node1.addItem(new Item(item))

      when:
      def result = grappStoreLayoutService.addNodeItem(layout.getId().toString(), node2.getId().toString(), item)

      then:
      result.getTarget().getItem() == item

      and:
      node1.getItems().isEmpty()
      node2.getItems().size() == 1

      and:
      result.getAffectedNodes().size() == 1
      result.getAffectedNodes()[0].getId() == node1.getId().toString()
   }
}
