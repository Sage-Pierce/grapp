package org.codegas.common.webservices.hal.api;

public class HalLink {

    private final String rel;

    private final String href;

    public HalLink(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public String getHref() {
        return href;
    }
}
