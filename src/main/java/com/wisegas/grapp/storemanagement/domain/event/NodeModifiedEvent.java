package com.wisegas.grapp.storemanagement.domain.event;

import com.wisegas.common.domain.model.DomainEvent;

import java.time.LocalDateTime;

public class NodeModifiedEvent implements DomainEvent {

   private final LocalDateTime occurredOn;
   private final String nodeID;

   public NodeModifiedEvent(String nodeID) {
      this.occurredOn = LocalDateTime.now();
      this.nodeID = nodeID;
   }

   public String getNodeID() {
      return nodeID;
   }

   @Override
   public LocalDateTime occurredOn() {
      return occurredOn;
   }
}
