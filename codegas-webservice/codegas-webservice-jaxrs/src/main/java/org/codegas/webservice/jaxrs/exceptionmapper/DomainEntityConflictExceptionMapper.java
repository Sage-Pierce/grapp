package org.codegas.webservice.jaxrs.exceptionmapper;

import org.codegas.commons.domain.exception.DomainEntityConflictException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DomainEntityConflictExceptionMapper implements ExceptionMapper<DomainEntityConflictException> {

    @Override
    public Response toResponse(DomainEntityConflictException e) {
        return Response.status(Response.Status.CONFLICT).type(MediaType.TEXT_PLAIN_TYPE).entity(e.getMessage()).build();
    }
}
