package com.wisegas.stores.domain.event;

import com.wisegas.common.domain.model.DomainEvent;

import java.time.LocalDateTime;

public class NodeModifiedEvent implements DomainEvent {

   private final LocalDateTime occurredOn;
   private final String nodeId;

   public NodeModifiedEvent(String nodeId) {
      this.occurredOn = LocalDateTime.now();
      this.nodeId = nodeId;
   }

   public String getNodeId() {
      return nodeId;
   }

   @Override
   public LocalDateTime occurredOn() {
      return occurredOn;
   }
}
