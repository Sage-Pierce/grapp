package org.codegas.common.domain.event;

public class DomainEventPublisherManager {

    private static final ThreadLocal<Integer> contextCount = ThreadLocal.withInitial(() -> 0);

    public void enterPublishingContext() {
        int count = contextCount.get();
        if (count == 0) {
            DomainEventPublisher.instance().reset();
        }
        contextCount.set(count + 1);
    }

    public void exitPublishingContext() {
        int count = contextCount.get() - 1;
        if (count == 0) {
            DomainEventPublisher.instance().reset();
        }
        contextCount.set(count);
    }
}
