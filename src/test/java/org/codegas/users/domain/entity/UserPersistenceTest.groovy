package org.codegas.users.domain.entity

import org.codegas.users.test.builder.UserBuilder
import org.codegas.commons.test.base.EntityPersistenceTest
import org.springframework.transaction.annotation.Transactional

@Transactional
class UserPersistenceTest extends EntityPersistenceTest<User> {

   @Override
   User createTestEntity() {
      UserBuilder.build()
   }
}
