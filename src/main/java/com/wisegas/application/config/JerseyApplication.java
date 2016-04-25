package com.wisegas.application.config;

import com.wisegas.common.webserver.jaxrs.exceptionmapper.EntityConflictExceptionMapper;
import com.wisegas.common.webserver.jaxrs.filter.CORSResponseFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyApplication extends ResourceConfig {

   public JerseyApplication() {
      // Register REST Endpoints
      packages("com.wisegas.application.restresource",
               "com.wisegas.itemmanagement.restresource",
               "com.wisegas.storemanagement.restresource",
               "com.wisegas.user.restresource");

      // Register Filters
      register(RequestContextFilter.class);
      register(CORSResponseFilter.class);

      // Register Mappers
      register(EntityConflictExceptionMapper.class);
   }
}
