package com.wisegas.common.test.base

import com.wisegas.common.test.entity.TestEntityManager
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.inject.Inject

@ContextConfiguration(["classpath*:META-INF/spring/test-context.xml"])
abstract class PersistenceTest extends Specification {

   @Inject
   protected TestEntityManager testEntityManager
}
