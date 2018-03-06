package org.codegas.common.webservices.jaxrs.translation;

import javax.ws.rs.core.Response;

import org.codegas.common.translation.api.Translator;

@FunctionalInterface
public interface ResponseTranslator<T> extends Translator<Response, T> {

}
