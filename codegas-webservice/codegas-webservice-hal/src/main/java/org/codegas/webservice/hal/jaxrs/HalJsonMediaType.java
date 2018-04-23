package org.codegas.webservice.hal.jaxrs;

import javax.ws.rs.core.MediaType;

public final class HalJsonMediaType extends MediaType {

    private static final HalJsonMediaType INSTANCE = new HalJsonMediaType();

    private HalJsonMediaType() {
        super("application", "hal+json");
    }

    public static HalJsonMediaType getInstance() {
        return INSTANCE;
    }
}
