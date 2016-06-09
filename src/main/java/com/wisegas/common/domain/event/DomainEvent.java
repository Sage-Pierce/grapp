package com.wisegas.common.domain.event;

import java.time.LocalDateTime;

public interface DomainEvent {
   LocalDateTime occurredOn();
}
