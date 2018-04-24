package org.codegas.shoppinglists.domain_impl.repository

import org.codegas.service.spock.RepositoryImplPersistenceTest
import org.codegas.shoppinglists.domain.entity.Shopper
import org.codegas.shoppinglists.domain.repository.ShopperRepository
import org.codegas.shoppinglists.test.builder.ShopperBuilder
import org.springframework.transaction.annotation.Transactional

import javax.inject.Inject

@Transactional
class ShopperRepositoryImplPersistenceTest extends RepositoryImplPersistenceTest<Shopper> {

   @Inject
   private ShopperRepository shopperRepository

   def "Shoppers can be found by their Email"() {
      given:
      Shopper shopper = testEntityManager.save(ShopperBuilder.build())

      when:
      def result = shopperRepository.findByEmail(shopper.getId())

      then:
      result.isPresent()

      and:
      shopper == result.get()
   }

   @Override
   Shopper createTestEntity() {
      ShopperBuilder.build()
   }
}
