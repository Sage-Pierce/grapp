package com.wisegas.common.webservices.jaxrs.translation;

import com.wisegas.common.lang.translation.api.Translator;

import javax.ws.rs.core.Response;

@FunctionalInterface
public interface ResponseTranslator<T> extends Translator<Response, T> {

}