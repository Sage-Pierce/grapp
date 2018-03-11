package org.codegas.commons.di.spring.web;

import org.codegas.commons.di.annotation.Request;
import org.codegas.commons.di.annotation.Session;
import org.springframework.context.annotation.Jsr330ScopeMetadataResolver;
import org.springframework.web.context.WebApplicationContext;

public class WebAppScopeMetaDataResolver extends Jsr330ScopeMetadataResolver {

    public WebAppScopeMetaDataResolver() {
        registerScope(Session.class.getName(), WebApplicationContext.SCOPE_SESSION);
        registerScope(Request.class.getName(), WebApplicationContext.SCOPE_REQUEST);
    }
}
