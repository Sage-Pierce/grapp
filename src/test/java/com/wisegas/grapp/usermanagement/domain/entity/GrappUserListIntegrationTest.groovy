package com.wisegas.grapp.usermanagement.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.grapp.usermanagement.test.builders.GrappUserListBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappUserListIntegrationTest extends EntityIntegrationTest<GrappUserList> {

   @Override
   GrappUserList createTestEntity() {
      GrappUserListBuilder.grappUserList()
   }
}
