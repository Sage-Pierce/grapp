package com.wisegas.grapp.storemanagement.domain.entity

import com.wisegas.common.lang.value.IdName
import com.wisegas.common.persistence.jpa.entity.EntityIntegrationTest
import com.wisegas.grapp.storemanagement.test.builders.GrappStoreNodeItemBuilder
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappStoreNodeItemIntegrationTest extends EntityIntegrationTest<GrappStoreNodeItem> {

   def "The Item on a GrappStoreNodeItem is persisted correctly"() {
      given:
      GrappStoreNodeItem grappStoreNodeItem = GrappStoreNodeItemBuilder.grappStoreNodeItem()
      IdName item = grappStoreNodeItem.getItem()
      testEntityManager.save(grappStoreNodeItem)

      when:
      testEntityManager.flush()
      testEntityManager.clear()

      and:
      def managedGrappStoreNodeItem = testEntityManager.getManagedEntity(grappStoreNodeItem)

      then:
      managedGrappStoreNodeItem.getItem() == item
   }

   @Override
   GrappStoreNodeItem createTestEntity() {
      GrappStoreNodeItemBuilder.grappStoreNodeItem()
   }
}
