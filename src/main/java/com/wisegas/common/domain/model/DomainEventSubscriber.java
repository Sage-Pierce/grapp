package com.wisegas.common.domain.model;

public interface DomainEventSubscriber<T extends DomainEvent> {
   Class<T> getSubscribedEventType();

   void handleEvent(T domainEvent);
}
