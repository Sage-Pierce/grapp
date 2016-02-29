package com.wisegas.grapp.domain.entity

import com.wisegas.grapp.test.Builders
import com.wisegas.test.BaseIntegrationTest
import com.wisegas.lang.GeoPoint
import com.wisegas.lang.GeoPolygon
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared

@Transactional
class GrappStoreLayoutIntegrationTest extends BaseIntegrationTest {

   @Shared
   GrappStoreLayout layout
   @Shared
   GrappStoreFeature feature

   @Override
   def setupSpecDB() {
      GrappStore store = Builders.grappStore()
      layout = store.getGrappStoreLayout()
      feature = layout.addFeature(new GeoPolygon([]))
      testEntityManager.save(store)
   }

   def "Features in a Layout can be reshaped by ID"() {
      given:
      def layout = testEntityManager.getManagedEntity(layout)

      expect:
      layout.reshapeFeature(feature.getId(), new GeoPolygon([new GeoPoint(1, 0)]))
   }
}
