package com.wisegas.common.domain.model;

public class DomainEventPublisherResetter {

   public void call() {
      DomainEventPublisher.instance().reset();
   }
}
