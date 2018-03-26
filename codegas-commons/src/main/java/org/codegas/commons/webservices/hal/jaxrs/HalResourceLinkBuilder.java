package org.codegas.commons.webservices.hal.jaxrs;

import javax.ws.rs.core.UriBuilder;

import org.codegas.commons.webservices.hal.api.HalLink;

public final class HalResourceLinkBuilder {

    private static final String SELF_REL = "self";

    private final Class resource;

    private String methodName;

    private Object[] pathArgs;

    private String[] queryParams;

    private HalResourceLinkBuilder(Class resource) {
        this.resource = resource;
    }

    public static HalResourceLinkBuilder linkTo(Class resource) {
        return new HalResourceLinkBuilder(resource);
    }

    public HalResourceLinkBuilder method(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public HalResourceLinkBuilder pathArgs(Object... pathArgs) {
        this.pathArgs = pathArgs;
        return this;
    }

    public HalResourceLinkBuilder queryParams(String... queryParams) {
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
        return new HalLink(rel, uriString + buildQueryFragment());
    }

    private String buildQueryFragment() {
        return queryParams == null || queryParams.length == 0 ? "" : "{?" + join(queryParams) + "}";
    }

    private static String join(String[] strings) {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String string : strings) {
            builder.append(first ? "" : ",");
            builder.append(string);
            first = false;
        }
        return builder.toString();
    }
}
