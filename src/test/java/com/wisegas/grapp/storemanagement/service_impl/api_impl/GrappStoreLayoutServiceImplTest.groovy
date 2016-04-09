package com.wisegas.grapp.storemanagement.service_impl.api_impl

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.common.test.ApplicationServiceTest
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreLayout
import com.wisegas.grapp.storemanagement.domain.entity.GrappStoreNode
import com.wisegas.grapp.storemanagement.domain.repository.GrappStoreLayoutRepository
import com.wisegas.grapp.storemanagement.domain.value.GrappStoreNodeType
import com.wisegas.grapp.storemanagement.test.builders.GrappStoreBuilder

class GrappStoreLayoutServiceImplTest extends ApplicationServiceTest {

   GrappStoreLayoutServiceImpl grappStoreLayoutService
   GrappStoreLayoutRepository grappStoreLayoutRepository

   def setup() {
      grappStoreLayoutRepository = Mock(GrappStoreLayoutRepository)
      grappStoreLayoutService = new GrappStoreLayoutServiceImpl(grappStoreLayoutRepository)
   }

   def "The result of adding a Node through the Service updates the node and notifies of affected Nodes"() {
      given:
      GrappStoreLayout layout = GrappStoreBuilder.grappStore().getGrappStoreLayout()
      GrappStoreNode oldEntrance = layout.addNode(GrappStoreNodeType.ENTRANCE, new GeoPoint(0, 0))
      GrappStoreNode regularNode = layout.addNode(GrappStoreNodeType.REGULAR, new GeoPoint(0, 1))

      and:
      grappStoreLayoutRepository.get(layout.getId()) >> layout

      when:
      def result = grappStoreLayoutService.addNode(layout.getId().toString(), GrappStoreNodeType.ENTRANCE.name(), new GeoPoint(1, 1))

      then:
      result.getTarget().getType() == GrappStoreNodeType.ENTRANCE.name()
      result.getTarget().getLocation() == new GeoPoint(1, 1)
      result.getAffectedNodes().size() == 2
      result.getAffectedNodes().collect { it.getId() }.contains(oldEntrance.getId().toString())
      !result.getAffectedNodes().collect { it.getId() }.contains(regularNode.getId().toString())
   }
}
