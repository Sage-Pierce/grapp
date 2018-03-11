package org.codegas.stores.domain.value;

public enum NodeType {
    ENTRANCE,
    CHECKOUT,
    REGULAR;

    public static NodeType defaultNonSingleton() {
        return REGULAR;
    }

    public boolean isSingleton() {
        return this != REGULAR;
    }
}
