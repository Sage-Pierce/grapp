package com.wisegas.grapp.config;

import com.wisegas.common.webserver.jersey.filter.CORSResponseFilter;
import com.wisegas.common.webserver.jersey.mapper.EntityConflictExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyApplication extends ResourceConfig {

   public JerseyApplication() {
      // Register REST Controllers
      packages("com.wisegas.grapp.restresource");

      // Register Features
      register(JacksonFeature.class);

      // Register Filters
      register(RequestContextFilter.class);
      register(CORSResponseFilter.class);

      // Register Mappers
      register(EntityConflictExceptionMapper.class);
   }
}
