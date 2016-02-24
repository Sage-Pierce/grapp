package com.wisegas.grapp.domain.entity

import com.wisegas.grapp.test.Builders
import com.wisegas.persistence.jpa.entity.EntityIntegrationTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared

@Transactional
class GrappUserIntegrationTest extends GrappEntityIntegrationTest<GrappUser> {
   @Shared
   GrappUser grappUser

   @Override
   def setupSpecDB() {
      super.setupSpecDB()
      grappUser = Builders.grappUser()
      grappUser.addGrappUserList("Test User List")
      testEntityManager.save(grappUser)
   }

   def "A GrappUser's Lists are lazy-loaded correctly when within a Transactional context"() {
      when: "The saved GrappUser is loaded"
      GrappUser savedGrappUser = testEntityManager.getManagedEntity(grappUser)

      then: "The GrappUser's lazily loaded Lists are correct"
      savedGrappUser.getGrappUserLists().size() == 1
      savedGrappUser.getGrappUserLists()[0].getName() == "Test User List"
   }
}
