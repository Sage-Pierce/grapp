package com.wisegas.grapp.usermanagement.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.grapp.usermanagement.test.builders.GrappUserBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class UserIntegrationTest extends EntityIntegrationTest<User> {

   def "A GrappUser's Lists are lazy-loaded correctly when within a Transactional context"() {
      when:
      User grappUser = GrappUserBuilder.grappUser()
      grappUser.addShoppingList("Test User List")
      testEntityManager.save(grappUser)
      testEntityManager.flush()
      testEntityManager.clear()

      and: "The saved User is loaded"
      User savedGrappUser = testEntityManager.getManagedEntity(grappUser)

      then: "The User's lazily loaded Lists are correct"
      savedGrappUser.getShoppingLists().size() == 1
      savedGrappUser.getShoppingLists()[0].getName() == "Test User List"
   }

   @Override
   User createTestEntity() {
      GrappUserBuilder.grappUser()
   }
}
