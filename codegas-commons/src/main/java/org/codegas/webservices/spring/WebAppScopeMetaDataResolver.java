package org.codegas.webservices.spring;

import org.codegas.commons.lang.annotation.RequestScope;
import org.codegas.commons.lang.annotation.SessionScope;
import org.springframework.context.annotation.Jsr330ScopeMetadataResolver;
import org.springframework.web.context.WebApplicationContext;

public class WebAppScopeMetaDataResolver extends Jsr330ScopeMetadataResolver {

    public WebAppScopeMetaDataResolver() {
        registerScope(SessionScope.class.getName(), WebApplicationContext.SCOPE_SESSION);
        registerScope(RequestScope.class.getName(), WebApplicationContext.SCOPE_REQUEST);
    }
}
