package org.codegas.stores.domain.event;

import java.time.LocalDateTime;

import org.codegas.common.domain.event.DomainEvent;

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
