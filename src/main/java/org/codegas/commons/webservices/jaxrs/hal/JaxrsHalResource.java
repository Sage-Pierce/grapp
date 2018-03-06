package org.codegas.commons.webservices.jaxrs.hal;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.codegas.commons.webservices.hal.api.HalRepresentation;

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
