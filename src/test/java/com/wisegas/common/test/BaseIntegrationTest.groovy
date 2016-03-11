package com.wisegas.common.test

import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.inject.Inject

@ContextConfiguration("classpath*:TestBeans.xml")
abstract class BaseIntegrationTest extends Specification {

   @Inject
   protected IntegrationTestEntityManager testEntityManager
}
