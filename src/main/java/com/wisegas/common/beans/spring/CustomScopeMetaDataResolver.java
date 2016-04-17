package com.wisegas.common.beans.spring;

import com.wisegas.common.beans.annotation.Session;
import org.springframework.context.annotation.Jsr330ScopeMetadataResolver;
import org.springframework.web.context.WebApplicationContext;

public class CustomScopeMetaDataResolver extends Jsr330ScopeMetadataResolver {

   public CustomScopeMetaDataResolver() {
      registerScope(Session.class.getName(), WebApplicationContext.SCOPE_SESSION);
   }
}
