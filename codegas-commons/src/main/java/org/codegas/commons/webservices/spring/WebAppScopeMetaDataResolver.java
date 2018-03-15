package org.codegas.commons.webservices.spring;

import org.codegas.commons.lang.annotation.Request;
import org.codegas.commons.lang.annotation.Session;
import org.springframework.context.annotation.Jsr330ScopeMetadataResolver;
import org.springframework.web.context.WebApplicationContext;

public class WebAppScopeMetaDataResolver extends Jsr330ScopeMetadataResolver {

    public WebAppScopeMetaDataResolver() {
        registerScope(Session.class.getName(), WebApplicationContext.SCOPE_SESSION);
        registerScope(Request.class.getName(), WebApplicationContext.SCOPE_REQUEST);
    }
}
