package com.wisegas.grapp.domain.entity

import com.wisegas.grapp.test.Builders
import com.wisegas.common.test.IntegrationTest
import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.lang.value.GeoPolygon
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappStoreLayoutIntegrationTest extends IntegrationTest {

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
