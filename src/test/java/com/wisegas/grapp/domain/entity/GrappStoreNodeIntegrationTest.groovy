package com.wisegas.grapp.domain.entity

import com.wisegas.grapp.test.Builders
import com.wisegas.lang.GeoPoint
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared

@Transactional
class GrappStoreNodeIntegrationTest extends GrappEntityIntegrationTest<GrappStoreNode> {
   @Shared
   GrappStoreNode grappStoreNode

   @Override
   def setupSpecDB() {
      super.setupSpecDB()
      grappStoreNode = Builders.grappStoreNode()
      grappStoreNode.setLocation(new GeoPoint(1d, 1d))
      testEntityManager.save(grappStoreNode)
   }

   def "A GrappStoreNode's location is persisted correctly"() {
      given:
      def savedGrappStoreNode1 = testEntityManager.getManagedEntity(grappStoreNode)

      expect:
      savedGrappStoreNode1.getLocation() == new GeoPoint(1d, 1d)
   }
}