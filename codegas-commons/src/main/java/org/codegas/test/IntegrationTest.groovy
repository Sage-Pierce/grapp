package org.codegas.test

import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(["classpath*:META-INF/spring/test-context.xml"])
abstract class IntegrationTest extends PersistenceTest {

}
