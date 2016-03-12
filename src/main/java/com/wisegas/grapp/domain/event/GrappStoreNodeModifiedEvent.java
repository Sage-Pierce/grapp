package com.wisegas.grapp.domain.event;

import com.wisegas.common.domain.model.DomainEvent;

import java.time.LocalDateTime;

public class GrappStoreNodeModifiedEvent implements DomainEvent {

   private final LocalDateTime occurredOn;
   private final String nodeID;

   public GrappStoreNodeModifiedEvent(String nodeID) {
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
