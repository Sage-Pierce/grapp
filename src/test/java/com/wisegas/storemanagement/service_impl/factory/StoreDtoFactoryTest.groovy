package com.wisegas.storemanagement.service_impl.factory

import com.wisegas.storemanagement.test.builders.StoreBuilder
import spock.lang.Specification

class StoreDtoFactoryTest extends Specification {

   def "A GrappStoreDTO can be created from a GrappStore"() {
      given:
      def grappStore = StoreBuilder.store()

      when:
      def result = StoreDtoFactory.createDto(grappStore)

      then:
      with(result) {
         id == grappStore.getId().toString()
         name == grappStore.getName()
         location == grappStore.getLocation()
      }
   }
}
