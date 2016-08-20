package com.wisegas.common.test.base

import com.wisegas.common.domain.event.DomainEventPublisher
import spock.lang.Specification

abstract class ApplicationServiceTest extends Specification {

   def setup() {
      DomainEventPublisher.instance().reset()
   }

   def cleanup() {
      DomainEventPublisher.instance().reset()
   }
}
