package com.wisegas.storemanagement.domain.entity

import com.wisegas.common.lang.spacial.GeoPoint
import com.wisegas.common.lang.spacial.GeoPolygon
import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.storemanagement.test.builders.StoreBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class StoreIntegrationTest extends EntityIntegrationTest<Store> {

   def "A Store's outer outline can be retrieved after saving"() {
      given:
      Store store = StoreBuilder.store()
      store.getStoreLayout().setOuterOutline(new GeoPolygon([new GeoPoint(0, 0), new GeoPoint(0, 1), new GeoPoint(1, 0)]));
      testEntityManager.save(store);
      testEntityManager.flush()
      testEntityManager.clear()

      and:
      Store savedStore = testEntityManager.getManagedEntity(store)

      when:
      def outerOutline = savedStore.getStoreLayout().getOuterOutline()

      then:
      outerOutline
      outerOutline.getVertices().size() == 3
      outerOutline.getVertices()[0] == new GeoPoint(0, 0)
      outerOutline.getVertices()[1] == new GeoPoint(0, 1)
      outerOutline.getVertices()[2] == new GeoPoint(1, 0)
   }

   @Override
   Store createTestEntity() {
      StoreBuilder.store()
   }
}
