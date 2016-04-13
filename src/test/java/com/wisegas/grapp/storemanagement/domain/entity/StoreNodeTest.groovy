package com.wisegas.grapp.storemanagement.domain.entity

import com.wisegas.common.domain.model.DomainEventPublisher
import com.wisegas.common.domain.model.DomainEventSubscriber
import com.wisegas.common.test.DomainEventAwareTest
import com.wisegas.grapp.storemanagement.domain.event.NodeModifiedEvent
import com.wisegas.grapp.storemanagement.domain.value.NodeType
import com.wisegas.grapp.storemanagement.test.builders.GrappStoreNodeBuilder

class StoreNodeTest extends DomainEventAwareTest {

   def "Changing a GrappStoreNode's Type causes a Domain Event to be published"() {
      given:
      Node node = GrappStoreNodeBuilder.grappStoreNode().having { it.type = NodeType.ENTRANCE }

      and:
      DomainEventSubscriber<NodeModifiedEvent> eventSubscriber = Mock(DomainEventSubscriber) {
         getSubscribedEventType() >> NodeModifiedEvent
      }
      DomainEventPublisher.instance().subscribe(eventSubscriber)

      when:
      node.setType(NodeType.REGULAR)

      then:
      1 * eventSubscriber.handleEvent(_ as NodeModifiedEvent) >> { args ->
         NodeModifiedEvent event = args[0]
         event.getNodeID() == node.getId().toString()
      }
   }
}
