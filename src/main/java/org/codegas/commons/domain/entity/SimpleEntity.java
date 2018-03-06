package org.codegas.commons.domain.entity;

import org.codegas.commons.lang.value.Id;

public abstract class SimpleEntity<T extends Id> {

    @Override
    public boolean equals(Object object) {
        return object != null && getClass().equals(object.getClass()) && hashCode() == object.hashCode();
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public abstract T getId();
}
