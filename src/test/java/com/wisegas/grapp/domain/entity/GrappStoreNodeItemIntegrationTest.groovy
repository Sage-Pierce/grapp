package com.wisegas.grapp.domain.entity

import com.wisegas.common.lang.value.IdName
import com.wisegas.grapp.test.Builders
import org.springframework.transaction.annotation.Transactional

@Transactional
class GrappStoreNodeItemIntegrationTest extends GrappEntityIntegrationTest<GrappStoreNodeItem> {

   def "The Item on a GrappStoreNodeItem is persisted correctly"() {
      given:
      GrappStoreNodeItem grappStoreNodeItem = Builders.grappStoreNodeItem()
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
}
