package com.wisegas.grapp.domain.entity

import com.wisegas.grapp.test.Builders
import com.wisegas.test.BaseIntegrationTest
import com.wisegas.lang.GeoPoint
import com.wisegas.lang.GeoPolygon
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappStoreLayoutIntegrationTest extends BaseIntegrationTest {

   def "Features in a Layout can be reshaped by ID"() {
      given:
      GrappStore store = Builders.grappStore()
      GrappStoreLayout layout = store.getGrappStoreLayout()
      GrappStoreFeature feature = layout.addFeature(new GeoPolygon([]))
      testEntityManager.save(store)
      testEntityManager.flush()
      testEntityManager.clear()

      and:
      def managedLayout = testEntityManager.getManagedEntity(layout)

      expect:
      managedLayout.reshapeFeature(feature.getId(), new GeoPolygon([new GeoPoint(1, 0)]))
   }
}
