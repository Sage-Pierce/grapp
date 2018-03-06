package org.codegas.stores.domain.entity

import org.codegas.commons.test.base.EntityPersistenceTest
import org.codegas.stores.test.builder.StoreBuilder
import org.codegas.commons.lang.spacial.GeoPoint
import org.codegas.commons.lang.spacial.GeoPolygon
import org.springframework.transaction.annotation.Transactional

@Transactional
class StorePersistenceTest extends EntityPersistenceTest<Store> {

   def "A Store's outer outline can be retrieved after saving"() {
      given:
      Store store = StoreBuilder.build()
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
      StoreBuilder.build()
   }
}
