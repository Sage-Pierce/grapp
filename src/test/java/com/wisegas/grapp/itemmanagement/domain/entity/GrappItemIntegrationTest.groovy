package com.wisegas.grapp.itemmanagement.domain.entity

import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.grapp.itemmanagement.test.builders.GrappItemBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappItemIntegrationTest extends EntityIntegrationTest<GrappItem> {

   @Override
   GrappItem createTestEntity() {
      GrappItemBuilder.grappItem()
   }
}