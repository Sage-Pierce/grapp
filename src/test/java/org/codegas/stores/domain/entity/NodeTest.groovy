package org.codegas.stores.domain.entity

import org.codegas.common.domain.event.DomainEventPublisher
import org.codegas.common.domain.event.DomainEventSubscriber
import org.codegas.common.test.base.ApplicationServiceTest
import org.codegas.stores.domain.event.NodeModifiedEvent
import org.codegas.stores.domain.value.NodeType
import org.codegas.stores.test.builder.NodeBuilder
import org.codegas.common.domain.event.DomainEventPublisher
import org.codegas.common.domain.event.DomainEventSubscriber
import org.codegas.stores.domain.event.NodeModifiedEvent
import org.codegas.stores.domain.value.NodeType

class NodeTest extends ApplicationServiceTest {

   def "Changing a Node's Type causes a Domain Event to be published"() {
      given:
      Node node = NodeBuilder.build()
      node.setType(NodeType.ENTRANCE)

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
         assert event.getNodeId() == node.getId().toString()
      }
   }
}
