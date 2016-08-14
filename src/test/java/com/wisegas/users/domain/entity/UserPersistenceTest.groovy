package com.wisegas.users.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityPersistenceTest
import com.wisegas.users.test.builder.UserBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class UserPersistenceTest extends EntityPersistenceTest<User> {

   @Override
   User createTestEntity() {
      UserBuilder.build()
   }
}
