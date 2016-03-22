package com.wisegas.common.webserver.jersey.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSResponseFilter implements ContainerResponseFilter {

   @Override
   public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
      MultivaluedMap<String, Object> headers = responseContext.getHeaders();
      headers.add("Access-Control-Allow-Origin", "*"); // "http://localhost:*"
      headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
   }
}
