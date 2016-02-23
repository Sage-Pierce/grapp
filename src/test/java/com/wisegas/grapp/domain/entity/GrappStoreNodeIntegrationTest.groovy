package com.wisegas.grapp.domain.entity

import com.wisegas.grapp.test.Builders
import com.wisegas.value.GeoPoint
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared

@Transactional
class GrappStoreNodeIntegrationTest extends EntityIntegrationTest<GrappStoreNode> {
   @Shared
   GrappStoreNode grappStoreNode1
   @Shared
   GrappStoreNode grappStoreNode2

   @Override
   def setupSpecDB() {
      super.setupSpecDB()

      grappStoreNode1 = Builders.grappStoreNode()
      grappStoreNode2 = Builders.grappStoreNode()
      grappStoreNode1.addLink(grappStoreNode2)

      grappStoreNode1.setLocation(new GeoPoint(1d, 1d))

      testEntityManager.save(grappStoreNode1)
   }

   def "A Connection shows up on both GrappNodes"() {
      given: "GrappNodes that are connected"
      def savedGrappNode1 = testEntityManager.getManagedEntity(grappStoreNode1)
      def savedGrappNode2 = testEntityManager.getManagedEntity(grappStoreNode2)

      when: "we get the nodes that each of the nodes are connected to"
      def node1LinkedNodes = savedGrappNode1.getLinkedNodes()
      def node2LinkedNodes = savedGrappNode2.getLinkedNodes()

      then: "they should both contain the other"
      node1LinkedNodes == [savedGrappNode2]
      node2LinkedNodes == [savedGrappNode1]
   }

   def "A GrappStoreNode's location is persisted correctly"() {
      given:
      def savedGrappStoreNode1 = testEntityManager.getManagedEntity(grappStoreNode1)

      expect:
      savedGrappStoreNode1.getLocation() == new GeoPoint(1d, 1d)
   }
}