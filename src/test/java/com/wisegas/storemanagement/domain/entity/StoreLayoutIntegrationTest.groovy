package com.wisegas.storemanagement.domain.entity

import com.wisegas.common.lang.spacial.GeoPoint
import com.wisegas.common.lang.spacial.GeoPolygon
import com.wisegas.common.test.base.IntegrationTest
import com.wisegas.storemanagement.test.builders.StoreBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class StoreLayoutIntegrationTest extends IntegrationTest {

   def "Features in a Layout can be reshaped by ID"() {
      given:
      Store store = StoreBuilder.store()
      StoreLayout layout = store.getStoreLayout()
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
