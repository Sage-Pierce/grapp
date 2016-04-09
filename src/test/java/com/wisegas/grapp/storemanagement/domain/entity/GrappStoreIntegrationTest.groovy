package com.wisegas.grapp.storemanagement.domain.entity

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.lang.value.GeoPolygon
import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.grapp.storemanagement.test.builders.GrappStoreBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappStoreIntegrationTest extends EntityIntegrationTest<GrappStore> {

   def "A GrappStore's outer outline can be retrieved after saving"() {
      given:
      GrappStore grappStore = GrappStoreBuilder.grappStore()
      grappStore.getGrappStoreLayout().setOuterOutline(new GeoPolygon([new GeoPoint(0, 0), new GeoPoint(0, 1), new GeoPoint(1, 0)]));
      testEntityManager.save(grappStore);
      testEntityManager.flush()
      testEntityManager.clear()

      and:
      GrappStore savedGrappStore = testEntityManager.getManagedEntity(grappStore)

      when:
      def outerOutline = savedGrappStore.getGrappStoreLayout().getOuterOutline()

      then:
      outerOutline
      outerOutline.getVertices().size() == 3
      outerOutline.getVertices()[0] == new GeoPoint(0, 0)
      outerOutline.getVertices()[1] == new GeoPoint(0, 1)
      outerOutline.getVertices()[2] == new GeoPoint(1, 0)
   }

   @Override
   GrappStore createTestEntity() {
      GrappStoreBuilder.grappStore()
   }
}
