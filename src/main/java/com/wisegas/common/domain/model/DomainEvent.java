package com.wisegas.common.domain.model;

import java.time.LocalDateTime;

public interface DomainEvent {
   LocalDateTime occurredOn();
}
