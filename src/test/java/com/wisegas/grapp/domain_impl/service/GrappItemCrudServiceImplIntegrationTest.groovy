package com.wisegas.grapp.domain_impl.service

import com.wisegas.common.lang.annotation.Transactional
import com.wisegas.common.test.IntegrationTest
import com.wisegas.grapp.domain.entity.GrappItem
import com.wisegas.grapp.test.Builders

import javax.inject.Inject

@Transactional
class GrappItemCrudServiceImplIntegrationTest extends IntegrationTest {

   @Inject
   private GrappItemCrudServiceImpl grappItemCrudService

   def "A GrappItem cannot be created or modified to a certain name if an Item of that name already exists"() {
      given:
      GrappItem existingItem1 = testEntityManager.save(Builders.grappItem())
      GrappItem existingItem2 = testEntityManager.save(Builders.grappItem())

      when:
      grappItemCrudService.createGeneralItem(existingItem1.getName())

      then:
      thrown(Exception)

      when:
      grappItemCrudService.createSubItem(existingItem1.getId(), existingItem2.getName())

      then:
      thrown(Exception)

      when:
      grappItemCrudService.updateName(existingItem2.getId(), existingItem1.getName())

      then:
      thrown(Exception)
   }
}
