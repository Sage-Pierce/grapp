package com.wisegas.grapp.storemanagement.domain.entity

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.grapp.storemanagement.domain.value.Item
import com.wisegas.grapp.storemanagement.test.builders.GrappStoreNodeBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappStoreNodeIntegrationTest extends EntityIntegrationTest<GrappStoreNode> {

   def "A GrappStoreNode's location is persisted correctly"() {
      given:
      GrappStoreNode grappStoreNode = GrappStoreNodeBuilder.grappStoreNode()
      grappStoreNode.setLocation(new GeoPoint(1d, 1d))
      testEntityManager.save(grappStoreNode)
      testEntityManager.flush()
      testEntityManager.clear()

      and:
      def managedGrappStoreNode = testEntityManager.getManagedEntity(grappStoreNode)

      expect:
      managedGrappStoreNode.getLocation() == new GeoPoint(1d, 1d)
   }

   def "GrappStoreNodes have their Items persisted correctly"() {
      given:
      GrappStoreNode grappStoreNode = GrappStoreNodeBuilder.grappStoreNode()

      and:
      Item item = new Item("CODE", "ITEM")
      grappStoreNode.addItem(item)

      and:
      testEntityManager.save(grappStoreNode)
      testEntityManager.flush()
      testEntityManager.clear()

      when:
      GrappStoreNode managedNode = testEntityManager.getManagedEntity(grappStoreNode)

      then:
      managedNode.containsItem(item)
   }

   @Override
   GrappStoreNode createTestEntity() {
      GrappStoreNodeBuilder.grappStoreNode()
   }
}