package com.wisegas.common.domain.model;

import java.util.ArrayList;
import java.util.List;

public class DomainEventPublisher {
   private static final ThreadLocal<DomainEventPublisher> instance = new ThreadLocal<DomainEventPublisher>() {
      @Override
      protected DomainEventPublisher initialValue() {
         return new DomainEventPublisher();
      }
   };

   private List<DomainEventSubscriber> subscribers = new ArrayList<>();
   private boolean publishing;

   public static DomainEventPublisher instance() {
      return instance.get();
   }

   public void reset() {
      if (!publishing) {
         subscribers.clear();
      }
   }

   public <T extends DomainEvent> void subscribe(DomainEventSubscriber<T> subscriber) {
      if (!publishing) {
         subscribers.add(subscriber);
      }
   }

   public <T extends DomainEvent> void publish(final T domainEvent) {
      if (!publishing && !subscribers.isEmpty()) {
         try {
            publishing = true;
            for (DomainEventSubscriber subscriber : subscribers) {
               Class<?> subscribedEventType = subscriber.getSubscribedEventType();
               if (subscribedEventType.isAssignableFrom(domainEvent.getClass())) {
                  subscriber.handleEvent(domainEvent);
               }
            }
         } finally {
            publishing = false;
         }
      }
   }
}
