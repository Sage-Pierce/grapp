package com.wisegas.grapp.usermanagement.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.grapp.usermanagement.test.builders.GrappUserListBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class UserListIntegrationTest extends EntityIntegrationTest<ShoppingList> {

   @Override
   ShoppingList createTestEntity() {
      GrappUserListBuilder.grappUserList()
   }
}
