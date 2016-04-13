package com.wisegas.grapp.storemanagement.service_impl.factory

import com.wisegas.grapp.storemanagement.test.builders.GrappStoreBuilder
import spock.lang.Specification

class GrappStoreDtoFactoryTest extends Specification {

   def "A GrappStoreDTO can be created from a GrappStore"() {
      given:
      def grappStore = GrappStoreBuilder.grappStore()

      when:
      def result = GrappStoreDtoFactory.createDto(grappStore)

      then:
      with(result) {
         id == grappStore.getId().toString()
         name == grappStore.getName()
         location == grappStore.getLocation()
      }
   }
}
