package com.wisegas.grapp.itemmanagement.domain_impl.repository

import com.wisegas.common.lang.annotation.Transactional
import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem
import com.wisegas.grapp.itemmanagement.domain.repository.GrappItemRepository
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCodeType
import com.wisegas.grapp.itemmanagement.test.builders.GrappItemBuilder

import javax.inject.Inject

@Transactional
class GrappItemRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<GrappItem> {

   @Inject
   private GrappItemRepository grappItemRepository

   def "All 'general' GrappItems can be found"() {
      given:
      GrappItem generalItem = GrappItemBuilder.grappItem()
      GrappItem subItem = generalItem.addSubItem(new GrappItemCode(GrappItemCodeType.MANUAL, "CODE"), "SUB ITEM")
      testEntityManager.save(generalItem)

      when:
      def result = grappItemRepository.getGeneralItems()

      then:
      result.contains(generalItem)
      !result.contains(subItem)
   }

   def "A GrappItem with a certain name can be found"() {
      given:
      GrappItem grappItem = testEntityManager.save(GrappItemBuilder.grappItem())

      expect:
      grappItemRepository.findByName(grappItem.getName()).isPresent()
      !grappItemRepository.findByName("BOGUS NAME").isPresent()
   }

   def "GrappItems can be found by code"() {
      given:
      GrappItem grappItem = testEntityManager.save(GrappItemBuilder.grappItem())

      when:
      def result = grappItemRepository.findByCode(grappItem.getId())

      then:
      result.isPresent()
      result.get().equals(grappItem)
   }

   @Override
   GrappItem createTestEntity() {
      return GrappItemBuilder.grappItem()
   }
}
