package com.wisegas.grapp.domain.entity

import com.wisegas.grapp.test.Builders
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappUserIntegrationTest extends GrappEntityIntegrationTest<GrappUser> {

   def "A GrappUser's Lists are lazy-loaded correctly when within a Transactional context"() {
      when:
      GrappUser grappUser = Builders.grappUser()
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
}
