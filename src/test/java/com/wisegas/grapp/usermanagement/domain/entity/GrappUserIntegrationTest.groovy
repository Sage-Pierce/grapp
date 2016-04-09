package com.wisegas.grapp.usermanagement.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.grapp.usermanagement.test.builders.GrappUserBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappUserIntegrationTest extends EntityIntegrationTest<GrappUser> {

   def "A GrappUser's Lists are lazy-loaded correctly when within a Transactional context"() {
      when:
      GrappUser grappUser = GrappUserBuilder.grappUser()
      grappUser.addGrappUserList("Test User List")
      testEntityManager.save(grappUser)
      testEntityManager.flush()
      testEntityManager.clear()

      and: "The saved GrappUser is loaded"
      GrappUser savedGrappUser = testEntityManager.getManagedEntity(grappUser)

      then: "The GrappUser's lazily loaded Lists are correct"
      savedGrappUser.getGrappUserLists().size() == 1
      savedGrappUser.getGrappUserLists()[0].getName() == "Test User List"
   }

   @Override
   GrappUser createTestEntity() {
      GrappUserBuilder.grappUser()
   }
}
