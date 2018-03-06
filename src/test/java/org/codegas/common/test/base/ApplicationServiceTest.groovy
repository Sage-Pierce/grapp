package org.codegas.common.test.base

import org.codegas.common.domain.event.DomainEventPublisher
import org.codegas.common.domain.event.DomainEventPublisher
import spock.lang.Specification

abstract class ApplicationServiceTest extends Specification {

   def setup() {
      DomainEventPublisher.instance().reset()
   }

   def cleanup() {
      DomainEventPublisher.instance().reset()
   }
}
