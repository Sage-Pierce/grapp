package org.codegas.stores.domain.entity

import org.codegas.common.lang.spacial.GeoPoint
import org.codegas.common.lang.spacial.GeoPolygon
import org.codegas.common.test.base.PersistenceTest
import org.codegas.stores.test.builder.StoreBuilder
import org.codegas.common.lang.spacial.GeoPoint
import org.codegas.common.lang.spacial.GeoPolygon
import org.springframework.transaction.annotation.Transactional

@Transactional
class StoreLayoutPersistenceTest extends PersistenceTest {

   def "Features in a Layout can be reshaped by ID"() {
      given:
      Store store = StoreBuilder.build()
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
