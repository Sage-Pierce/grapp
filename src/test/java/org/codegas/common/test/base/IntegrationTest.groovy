package org.codegas.common.test.base

import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(["classpath*:META-INF/spring/test-context.xml"])
abstract class IntegrationTest extends PersistenceTest {

}
