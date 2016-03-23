package com.wisegas.common.webserver.jaxrs.hal;

import com.wisegas.common.webserver.hal.api.HalLink;

import javax.ws.rs.core.UriBuilder;

public final class JaxrsHalResourceLinkBuilder {

   private static final String RELATIVE_REST_API_ROOT = "/rest";
   private static final String SELF_REL = "self";

   private Class resource;
   private String methodName;
   private Object[] pathArgs;
   private String[] queryParams;

   public static JaxrsHalResourceLinkBuilder linkTo(Class resource) {
      return new JaxrsHalResourceLinkBuilder(resource);
   }

   public JaxrsHalResourceLinkBuilder method(String methodName) {
      this.methodName = methodName;
      return this;
   }

   public JaxrsHalResourceLinkBuilder pathArgs(Object... pathArgs) {
      this.pathArgs = pathArgs;
      return this;
   }

   public JaxrsHalResourceLinkBuilder queryParams(String... queryParams) {
      this.queryParams = queryParams;
      return this;
   }

   public HalLink withSelfRel() {
      return withRel(SELF_REL);
   }

   public HalLink withRel(String rel) {
      UriBuilder uriBuilder = UriBuilder.fromResource(resource);
      uriBuilder = methodName == null ? uriBuilder : uriBuilder.path(resource, methodName);
      String uriString = pathArgs == null ? uriBuilder.toTemplate() : uriBuilder.build(pathArgs).toString();
      return new HalLink(rel, RELATIVE_REST_API_ROOT + uriString + buildQueryFragment());
   }

   private String buildQueryFragment() {
      return queryParams == null || queryParams.length == 0 ? "" : "{?" + join(queryParams, ",") + "}";
   }

   private static String join(String[] strings, String separator) {
      StringBuilder builder = new StringBuilder();
      boolean first = true;
      for (String string : strings) {
         builder.append(first ? "" : separator);
         builder.append(string);
         first = false;
      }
      return builder.toString();
   }

   private JaxrsHalResourceLinkBuilder(Class resource) {
      this.resource = resource;
   }
}
