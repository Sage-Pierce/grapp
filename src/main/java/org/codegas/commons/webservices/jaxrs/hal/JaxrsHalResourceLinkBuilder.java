package org.codegas.commons.webservices.jaxrs.hal;

import javax.ws.rs.core.UriBuilder;

import org.codegas.commons.webservices.hal.api.HalLink;

public final class JaxrsHalResourceLinkBuilder {

    private static final String SELF_REL = "self";

    private final Class resource;

    private String methodName;

    private Object[] pathArgs;

    private String[] queryParams;

    private JaxrsHalResourceLinkBuilder(Class resource) {
        this.resource = resource;
    }

    public static JaxrsHalResourceLinkBuilder linkTo(Class resource) {
        return new JaxrsHalResourceLinkBuilder(resource);
    }

    public JaxrsHalResourceLinkBuilder method(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public JaxrsHalResourceLinkBuilder pathArgs(Object... pathArgs) {
        this.pathArgs = pathArgs;
        return this;
    }

    public JaxrsHalResourceLinkBuilder queryParams(String... queryParams) {
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
