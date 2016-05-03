package com.wisegas.common.webservices.jaxrs.hal;

import com.wisegas.common.webservices.hal.api.HalRepresentation;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public abstract class JaxrsHalResource {
   @Context
   protected UriInfo uriInfo;

   protected static Response buildHalResponse(HalRepresentation halRepresentation) {
      return buildHalResponse(Response.Status.OK, halRepresentation);
   }

   protected static Response buildHalResponse(Response.Status status, HalRepresentation halRepresentation) {
      return Response.status(status).entity(halRepresentation.toString()).build();
   }
}
