package com.wisegas.grapp.domain.entity

import com.wisegas.common.lang.value.GeoPoint
import com.wisegas.grapp.domain.value.GrappStoreNodeType
import com.wisegas.grapp.test.Builders
import spock.lang.Specification

class GrappStoreLayoutTest extends Specification {

   def "Adding a singleton-Type Node to a Layout which already has one of that Type causes the original one to become whatever the default non-singleton type is"() {
      given:
      GrappStoreLayout grappStoreLayout = Builders.grappStoreLayout()

      and:
      GrappStoreNode oldEntrance = grappStoreLayout.addNode(singletonType, new GeoPoint(0, 0))

      when:
      GrappStoreNode newEntrance = grappStoreLayout.addNode(singletonType, new GeoPoint(1, 1))

      then:
      oldEntrance.getType() == GrappStoreNodeType.defaultNonSingleton()
      newEntrance.getType() == singletonType

      where:
      singletonType << GrappStoreNodeType.values().findAll { it.isSingleton() }
   }
}
