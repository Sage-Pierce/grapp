package com.wisegas.grapp.domain.entity

import com.wisegas.grapp.test.Builders
import com.wisegas.value.GeoPoint
import com.wisegas.value.GeoPolygon
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared

@Transactional
class GrappStoreIntegrationTest extends EntityIntegrationTest<GrappStore> {

   @Shared
   GrappStore grappStore

   @Override
   def setupSpecDB() {
      super.setupSpecDB()

      grappStore = Builders.grappStore()
      grappStore.getGrappStoreLayout().setOuterOutline(new GeoPolygon([new GeoPoint(0, 0), new GeoPoint(0, 1), new GeoPoint(1, 0)]));

      testEntityManager.save(grappStore);
   }

   def "A GrappStore's outer outline can be retrieved after saving"() {
      given:
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
}
