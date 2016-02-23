package com.wisegas.webserver.hal;

import com.wisegas.webserver.hal.api.HALLink;

import javax.ws.rs.core.UriBuilder;

public final class HALResourceLinkBuilder {

   private static final String RELATIVE_REST_API_ROOT = "/rest";
   private static final String SELF_REL = "self";

   private Class controller;
   private String methodName;
   private Object[] pathArgs;
   private String[] queryParams;

   public static HALResourceLinkBuilder linkTo(Class controller) {
      return new HALResourceLinkBuilder(controller);
   }

   public HALResourceLinkBuilder method(String methodName) {
      this.methodName = methodName;
      return this;
   }

   public HALResourceLinkBuilder pathArgs(Object... pathArgs) {
      this.pathArgs = pathArgs;
      return this;
   }

   public HALResourceLinkBuilder queryParams(String... queryParams) {
      this.queryParams = queryParams;
      return this;
   }

   public HALLink withSelfRel() {
      return withRel(SELF_REL);
   }

   public HALLink withRel(String rel) {
      UriBuilder uriBuilder = UriBuilder.fromResource(controller);
      uriBuilder = methodName == null ? uriBuilder : uriBuilder.path(controller, methodName);
      String uriString = pathArgs == null ? uriBuilder.toTemplate() : uriBuilder.build(pathArgs).toString();
      return new HALLink(rel, RELATIVE_REST_API_ROOT + uriString + buildQueryFragment());
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

   private HALResourceLinkBuilder(Class controller) {
      this.controller = controller;
   }
}
