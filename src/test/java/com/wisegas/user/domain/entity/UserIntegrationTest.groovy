package com.wisegas.user.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.user.test.builders.UserBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class UserIntegrationTest extends EntityIntegrationTest<User> {

   @Override
   User createTestEntity() {
      UserBuilder.user()
   }
}