package com.wisegas.application.config;

import com.wisegas.common.webservices.jaxrs.exceptionmapper.EntityConflictExceptionMapper;
import com.wisegas.common.webservices.jaxrs.exceptionmapper.IllegalArgumentExceptionMapper;
import com.wisegas.common.webservices.jaxrs.filter.CORSResponseFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication extends ResourceConfig {

   public JerseyApplication() {
      // Register REST Endpoints
      packages("com.wisegas.application.restresource",
               "com.wisegas.user.restresource",
               "com.wisegas.storemanagement.restresource",
               "com.wisegas.itemmanagement.restresource",
               "com.wisegas.shoppinglist.restresource",
               "com.wisegas.pathgeneration.restresource");

      // Register Filters
      register(CORSResponseFilter.class);

      // Register Mappers
      register(EntityConflictExceptionMapper.class);
      register(IllegalArgumentExceptionMapper.class);
   }
}
