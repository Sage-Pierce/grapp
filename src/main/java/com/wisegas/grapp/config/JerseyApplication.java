package com.wisegas.grapp.config;

import com.wisegas.webserver.jersey.filters.CORSResponseFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyApplication extends ResourceConfig {

   public JerseyApplication() {
      // Register REST Controllers
      packages("com.wisegas.grapp.webserviceresource");

      // Register Filters
      register(RequestContextFilter.class);
      register(CORSResponseFilter.class);

      // Register Features
      register(JacksonFeature.class);
   }
}
