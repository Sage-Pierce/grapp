package com.wisegas.grapp.storemanagement.domain.entity

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.lang.value.GeoPolygon
import com.wisegas.common.test.IntegrationTest
import com.wisegas.grapp.storemanagement.test.builders.GrappStoreBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class StoreLayoutIntegrationTest extends IntegrationTest {

   def "Features in a Layout can be reshaped by ID"() {
      given:
      Store store = GrappStoreBuilder.grappStore()
      Layout layout = store.getLayout()
      Feature feature = layout.addFeature(new GeoPolygon([]))
      testEntityManager.save(store)
      testEntityManager.flush()
      testEntityManager.clear()

      and:
      def managedLayout = testEntityManager.getManagedEntity(layout)

      expect:
      managedLayout.reshapeFeature(feature.getId(), new GeoPolygon([new GeoPoint(1, 0)]))
   }
}
