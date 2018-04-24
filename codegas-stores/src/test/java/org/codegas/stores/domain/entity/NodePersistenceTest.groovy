package org.codegas.stores.domain.entity

import org.codegas.service.spock.EntityPersistenceTest
import org.codegas.stores.test.builder.NodeBuilder
import org.codegas.commons.lang.spacial.GeoPoint
import org.codegas.stores.domain.value.Item
import org.springframework.transaction.annotation.Transactional

@Transactional
class NodePersistenceTest extends EntityPersistenceTest<Node> {

   def "A Node's location is persisted correctly"() {
      given:
      Node node = NodeBuilder.build()
      node.setLocation(new GeoPoint(1d, 1d))
      testEntityManager.save(node)
      testEntityManager.flush()
      testEntityManager.clear()

      and:
      def managedNode = testEntityManager.getManagedEntity(node)

      expect:
      managedNode.getLocation() == new GeoPoint(1d, 1d)
   }

   def "Nodes have their Items persisted correctly"() {
      given:
      Node node = NodeBuilder.build()

      and:
      Item item = new Item("CODE", "ITEM")
      node.addItem(item)

      and:
      testEntityManager.save(node)
      testEntityManager.flush()
      testEntityManager.clear()

      when:
      Node managedNode = testEntityManager.getManagedEntity(node)

      then:
      managedNode.containsItem(item)
   }

   @Override
   Node createTestEntity() {
      NodeBuilder.build()
   }
}