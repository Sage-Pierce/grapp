package com.wisegas.common.test.base

import com.wisegas.common.test.entity.IntegrationTestEntityManager
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.inject.Inject

@ContextConfiguration(["classpath*:META-INF/spring/test-context.xml"])
abstract class IntegrationTest extends Specification {

   @Inject
   protected IntegrationTestEntityManager testEntityManager
}
