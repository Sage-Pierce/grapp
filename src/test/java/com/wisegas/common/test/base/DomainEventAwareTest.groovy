package com.wisegas.common.test.base

import com.wisegas.common.domain.model.DomainEventPublisher
import spock.lang.Specification

abstract class DomainEventAwareTest extends Specification {

   def setup() {
      DomainEventPublisher.instance().reset()
   }

   def cleanup() {
      DomainEventPublisher.instance().reset()
   }
}
