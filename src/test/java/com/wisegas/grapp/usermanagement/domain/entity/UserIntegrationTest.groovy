package com.wisegas.grapp.usermanagement.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.grapp.usermanagement.test.builders.UserBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class UserIntegrationTest extends EntityIntegrationTest<User> {

   @Override
   User createTestEntity() {
      UserBuilder.user()
   }
}
