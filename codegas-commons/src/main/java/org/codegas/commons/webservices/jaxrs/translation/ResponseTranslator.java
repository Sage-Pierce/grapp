package org.codegas.commons.webservices.jaxrs.translation;

import javax.ws.rs.core.Response;

import org.codegas.commons.translation.api.Translator;

@FunctionalInterface
public interface ResponseTranslator<T> extends Translator<Response, T> {

}
