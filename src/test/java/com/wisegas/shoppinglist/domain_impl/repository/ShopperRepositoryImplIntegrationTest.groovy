package com.wisegas.shoppinglist.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.shoppinglist.domain.entity.Shopper
import com.wisegas.shoppinglist.domain.repository.ShopperRepository
import com.wisegas.shoppinglist.test.builders.ShopperBuilder
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class ShopperRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<Shopper> {

   @Inject
   private ShopperRepository shopperRepository

   def "Shoppers can be found by their Email"() {
      given:
      Shopper shopper = testEntityManager.save(ShopperBuilder.shopper())

      when:
      def result = shopperRepository.findByEmail(shopper.getId())

      then:
      result.isPresent()

      and:
      shopper == result.get()
   }

   @Override
   Shopper createTestEntity() {
      ShopperBuilder.shopper()
   }
}
