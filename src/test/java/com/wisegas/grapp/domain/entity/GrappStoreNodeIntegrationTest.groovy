package com.wisegas.grapp.domain.entity

import com.wisegas.grapp.test.Builders
import com.wisegas.common.lang.value.GeoPoint
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappStoreNodeIntegrationTest extends GrappEntityIntegrationTest<GrappStoreNode> {

   def "A GrappStoreNode's location is persisted correctly"() {
      given:
      GrappStoreNode grappStoreNode = Builders.grappStoreNode()
      grappStoreNode.setLocation(new GeoPoint(1d, 1d))
      testEntityManager.save(grappStoreNode)
      testEntityManager.flush()
      testEntityManager.clear()

      and:
      def managedGrappStoreNode = testEntityManager.getManagedEntity(grappStoreNode)

      expect:
      managedGrappStoreNode.getLocation() == new GeoPoint(1d, 1d)
   }
}