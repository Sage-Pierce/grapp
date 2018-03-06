package org.codegas.commons.webservices.jaxrs.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codegas.commons.domain.exception.EntityConflictException;

@Provider
public class EntityConflictExceptionMapper implements ExceptionMapper<EntityConflictException> {

    @Override
    public Response toResponse(EntityConflictException e) {
        return Response.status(Response.Status.CONFLICT).type(MediaType.TEXT_PLAIN_TYPE).entity(e.getMessage()).build();
    }
}
