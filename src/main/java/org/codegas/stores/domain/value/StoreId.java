package org.codegas.stores.domain.value;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import org.codegas.common.lang.value.AbstractId;

@Embeddable
public class StoreId extends AbstractId {

    @Basic
    private String id;

    public static StoreId generate() {
        return new StoreId(generateValue());
    }

    public static StoreId fromString(String string) {
        return new StoreId(string);
    }

    protected StoreId() {

    }

    private StoreId(String id) {
        this.id = id;
    }

    @Override
    public Object idHash() {
        return id;
    }
}
