package com.wisegas.grapp.config;

import com.wisegas.common.webserver.jaxrs.filter.CORSResponseFilter;
import com.wisegas.common.webserver.jaxrs.mapper.EntityConflictExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyApplication extends ResourceConfig {

   public JerseyApplication() {
      // Register REST Endpoints
      packages("com.wisegas.grapp.restresource",
               "com.wisegas.grapp.itemmanagement.restresource",
               "com.wisegas.grapp.usermanagement.restresource");

      // Register Features
      register(JacksonFeature.class);

      // Register Filters
      register(RequestContextFilter.class);
      register(CORSResponseFilter.class);

      // Register Mappers
      register(EntityConflictExceptionMapper.class);
   }
}
