package com.wisegas.common.webserver.jersey.hal;

import com.wisegas.common.webserver.hal.api.HalRepresentation;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public abstract class JerseyHalResource {
   @Context
   protected UriInfo uriInfo;

   protected static Response buildHalResponse(HalRepresentation halRepresentation) {
      return Response.ok().entity(halRepresentation.toString()).build();
   }
}
