package org.codegas.commons.test.base

import org.codegas.commons.test.entity.TestEntityManager
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.inject.Inject

@ContextConfiguration(["classpath*:META-INF/spring/test-persistence-context.xml"])
abstract class PersistenceTest extends Specification {

   @Inject
   protected TestEntityManager testEntityManager
}