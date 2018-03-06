package org.codegas.common.domain.event;

import java.time.LocalDateTime;

public interface DomainEvent {

    LocalDateTime occurredOn();
}
