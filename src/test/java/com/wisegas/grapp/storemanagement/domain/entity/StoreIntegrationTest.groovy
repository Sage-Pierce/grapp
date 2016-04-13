package com.wisegas.grapp.storemanagement.domain.entity

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.lang.value.GeoPolygon
import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.grapp.storemanagement.test.builders.StoreBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class StoreIntegrationTest extends EntityIntegrationTest<Store> {

   def "A GrappStore's outer outline can be retrieved after saving"() {
      given:
      Store grappStore = StoreBuilder.store()
      grappStore.getLayout().setOuterOutline(new GeoPolygon([new GeoPoint(0, 0), new GeoPoint(0, 1), new GeoPoint(1, 0)]));
      testEntityManager.save(grappStore);
      testEntityManager.flush()
      testEntityManager.clear()

      and:
      Store savedGrappStore = testEntityManager.getManagedEntity(grappStore)

      when:
      def outerOutline = savedGrappStore.getLayout().getOuterOutline()

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
