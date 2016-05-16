package com.wisegas.storemanagement.service_impl.api_impl

import com.wisegas.common.lang.spacial.GeoPoint
import com.wisegas.common.lang.value.CodeName
import com.wisegas.common.test.base.ApplicationServiceTest
import com.wisegas.storemanagement.domain.entity.Node
import com.wisegas.storemanagement.domain.entity.StoreLayout
import com.wisegas.storemanagement.domain.repository.StoreLayoutRepository
import com.wisegas.storemanagement.domain.value.Item
import com.wisegas.storemanagement.domain.value.NodeType
import com.wisegas.storemanagement.test.builders.StoreLayoutBuilder

class StoreStoreLayoutServiceImplTest extends ApplicationServiceTest {

   StoreLayoutServiceImpl layoutService
   StoreLayoutRepository layoutRepository

   def setup() {
      layoutRepository = Mock(StoreLayoutRepository)
      layoutService = new StoreLayoutServiceImpl(layoutRepository)
   }

   def "The result of adding a Node through the Service updates the node and notifies of affected Nodes"() {
      given:
      StoreLayout layout = StoreLayoutBuilder.storeLayout()
      Node oldEntrance = layout.addNode(NodeType.ENTRANCE, new GeoPoint(0, 0))
      Node regularNode = layout.addNode(NodeType.REGULAR, new GeoPoint(0, 1))

      and:
      layoutRepository.get(layout.getId()) >> layout

      when:
      def result = layoutService.addNode(layout.getId().toString(), NodeType.ENTRANCE.name(), new GeoPoint(1, 1))

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
      StoreLayout layout = StoreLayoutBuilder.storeLayout()

      and:
      layoutRepository.get(layout.getId()) >> layout

      and:
      Node node1 = layout.addNode(NodeType.REGULAR, new GeoPoint(0, 0))
      Node node2 = layout.addNode(NodeType.REGULAR, new GeoPoint(0, 0))

      and:
      node1.addItem(new Item(item))

      when:
      def result = layoutService.addNodeItem(layout.getId().toString(), node2.getId().toString(), item)

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
