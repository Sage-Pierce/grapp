package com.wisegas.test

import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.inject.Inject

@ContextConfiguration("classpath*:META-INF/Beans.xml")
abstract class BaseIntegrationTest extends Specification {

   @Inject
   protected IntegrationTestEntityManager testEntityManager
}
