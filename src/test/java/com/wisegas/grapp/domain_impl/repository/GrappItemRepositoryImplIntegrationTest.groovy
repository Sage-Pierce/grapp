package com.wisegas.grapp.domain_impl.repository

import com.wisegas.common.lang.annotation.Transactional
import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImplIntegrationTest
import com.wisegas.grapp.domain.entity.GrappItem
import com.wisegas.grapp.domain.repository.GrappItemRepository
import com.wisegas.grapp.domain.value.GrappItemCode
import com.wisegas.grapp.domain.value.GrappItemCodeType
import com.wisegas.grapp.test.Builders

import javax.inject.Inject

@Transactional
class GrappItemRepositoryImplIntegrationTest extends GenericRepositoryImplIntegrationTest<GrappItem> {

   @Inject
   private GrappItemRepository grappItemRepository

   def "All 'general' GrappItems can be found"() {
      given:
      GrappItem generalItem = Builders.grappItem()
      GrappItem subItem = generalItem.addSubItem("SUB ITEM")
      testEntityManager.save(generalItem)

      when:
      def result = grappItemRepository.getGeneralItems()

      then:
      result.contains(generalItem)
      !result.contains(subItem)
   }

   def "A GrappItem with a certain name can be found"() {
      given:
      GrappItem grappItem = testEntityManager.save(Builders.grappItem())

      expect:
      grappItemRepository.findByName(grappItem.getName()).isPresent()
      !grappItemRepository.findByName("BOGUS NAME").isPresent()
   }

   def "GrappItems can be found by code"() {
      given:
      GrappItemCode code = new GrappItemCode(GrappItemCodeType.NACS, "12-34-56")
      GrappItem grappItem = Builders.grappItem()
      grappItem.addCode(code)
      testEntityManager.save(grappItem)

      when:
      def result = grappItemRepository.findByCode(code)

      then:
      result.isPresent()
      result.get().equals(grappItem)
   }
}
