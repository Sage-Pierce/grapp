package com.wisegas.grapp.domain.entity

import com.wisegas.common.domain.model.DomainEventPublisher
import com.wisegas.common.domain.model.DomainEventSubscriber
import com.wisegas.common.test.DomainEventAwareTest
import com.wisegas.grapp.domain.event.GrappStoreNodeModifiedEvent
import com.wisegas.grapp.domain.value.GrappStoreNodeType
import com.wisegas.grapp.test.Builders

class GrappStoreNodeTest extends DomainEventAwareTest {

   def "Changing a GrappStoreNode's Type causes a Domain Event to be published"() {
      given:
      GrappStoreNode node = Builders.grappStoreNode().having { it.type = GrappStoreNodeType.ENTRANCE }

      and:
      DomainEventSubscriber<GrappStoreNodeModifiedEvent> eventSubscriber = Mock(DomainEventSubscriber) {
         getSubscribedEventType() >> GrappStoreNodeModifiedEvent
      }
      DomainEventPublisher.instance().subscribe(eventSubscriber)

      when:
      node.setType(GrappStoreNodeType.REGULAR)

      then:
      1 * eventSubscriber.handleEvent(_ as GrappStoreNodeModifiedEvent) >> { args ->
         GrappStoreNodeModifiedEvent event = args[0]
         event.getNodeID() == node.getId().toString()
      }
   }
}
