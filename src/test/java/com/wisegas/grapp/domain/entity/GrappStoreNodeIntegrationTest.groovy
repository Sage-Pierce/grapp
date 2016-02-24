package com.wisegas.grapp.domain.entity

import com.wisegas.grapp.test.Builders
import com.wisegas.lang.GeoPoint
import com.wisegas.persistence.jpa.entity.EntityIntegrationTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared

@Transactional
class GrappStoreNodeIntegrationTest extends GrappEntityIntegrationTest<GrappStoreNode> {
   @Shared
   GrappStoreNode grappStoreNode1

   @Override
   def setupSpecDB() {
      super.setupSpecDB()
      grappStoreNode1 = Builders.grappStoreNode()
      grappStoreNode1.setLocation(new GeoPoint(1d, 1d))
      testEntityManager.save(grappStoreNode1)
   }

   def "A GrappStoreNode's location is persisted correctly"() {
      given:
      def savedGrappStoreNode1 = testEntityManager.getManagedEntity(grappStoreNode1)

      expect:
      savedGrappStoreNode1.getLocation() == new GeoPoint(1d, 1d)
   }
}