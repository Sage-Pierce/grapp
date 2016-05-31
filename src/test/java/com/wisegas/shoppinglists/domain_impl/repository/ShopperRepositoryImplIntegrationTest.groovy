package com.wisegas.shoppinglists.domain_impl.repository

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.shoppinglists.domain.entity.Shopper
import com.wisegas.shoppinglists.domain.repository.ShopperRepository
import com.wisegas.shoppinglists.test.builders.ShopperBuilder
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
