package com.wisegas.grapp.service_impl.factory

import com.wisegas.grapp.test.Builders
import spock.lang.Specification

class GrappStoreDTOFactoryTest extends Specification {

   def "A GrappStoreDTO can be created from a GrappStore"() {
      given:
      def grappStore = Builders.grappStore()

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
