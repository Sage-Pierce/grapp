package com.wisegas.storemanagement.domain.entity

import com.wisegas.common.domain.model.DomainEventPublisher
import com.wisegas.common.domain.model.DomainEventSubscriber
import com.wisegas.common.test.DomainEventAwareTest
import com.wisegas.storemanagement.domain.event.NodeModifiedEvent
import com.wisegas.storemanagement.domain.value.NodeType
import com.wisegas.storemanagement.test.builders.NodeBuilder

class StoreNodeTest extends DomainEventAwareTest {

   def "Changing a GrappStoreNode's Type causes a Domain Event to be published"() {
      given:
      Node node = NodeBuilder.node().having { it.type = NodeType.ENTRANCE }

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
