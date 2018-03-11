package org.codegas.stores.service_impl.factory

import org.codegas.stores.test.builder.StoreBuilder
import org.codegas.stores.test.builder.StoreBuilder
import spock.lang.Specification

class StoreDtoFactoryTest extends Specification {

   def "A StoreDTO can be created from a Store"() {
      given:
      def store = StoreBuilder.build()

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
