package com.wisegas.grapp.service_impl.factory

import com.wisegas.grapp.domain.entity.GrappStore
import com.wisegas.grapp.domain.entity.GrappUser
import com.wisegas.common.lang.value.GeoPoint
import spock.lang.Specification

class GrappStoreDTOFactoryTest extends Specification {

   def "A GrappStoreDTO can be created from a GrappStore"() {
      given:
      def grappStore = new GrappStore(new GrappUser(), "Test Store", new GeoPoint(0, 0))

      when:
      def result = GrappStoreDTOFactory.createDTO(grappStore)

      then:
      with(result) {
         id == grappStore.getId().toString()
         name == grappStore.getName()
         location == grappStore.getLocation()
      }
   }
}
