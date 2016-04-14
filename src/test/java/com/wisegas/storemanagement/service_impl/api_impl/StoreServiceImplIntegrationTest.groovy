package com.wisegas.storemanagement.service_impl.api_impl

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.test.IntegrationTest
import com.wisegas.storemanagement.service.api.StoreService

import javax.inject.Inject

public class StoreServiceImplIntegrationTest extends IntegrationTest {

   @Inject
   private StoreService storeService

   def "We can create a Store through the Service"() {
      when:
      def result = storeService.create("TEST STORE", new GeoPoint(1, 2))

      then:
      result.getId()
      result.getName() == "TEST STORE"
      result.getLocation() == new GeoPoint(1, 2)
   }
}