package org.codegas.common.webservices.jaxrs.mediatype;

import javax.ws.rs.core.MediaType;

public final class HalJson extends MediaType {

    private static final HalJson instance = new HalJson();

    private HalJson() {
        super("application", "hal+json");
    }

    public static HalJson getInstance() {
        return instance;
    }
}
