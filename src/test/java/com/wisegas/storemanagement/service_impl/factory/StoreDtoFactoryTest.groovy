package com.wisegas.storemanagement.service_impl.factory

import com.wisegas.storemanagement.test.builders.StoreBuilder
import spock.lang.Specification

class StoreDtoFactoryTest extends Specification {

   def "A StoreDTO can be created from a Store"() {
      given:
      def store = StoreBuilder.store()

      when:
      def result = StoreDtoFactory.createDto(store)

      then:
      with(result) {
         id == store.getId().toString()
         name == store.getName()
         location == store.getLocation()
      }
   }
}
