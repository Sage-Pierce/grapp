package org.codegas.stores.domain.value;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import org.codegas.common.lang.value.AbstractId;

@Embeddable
public class StoreLayoutId extends AbstractId {

    @Basic
    private String id;

    public static StoreLayoutId generate() {
        return new StoreLayoutId(generateValue());
    }

    public static StoreLayoutId fromString(String string) {
        return new StoreLayoutId(string);
    }

    protected StoreLayoutId() {

    }

    private StoreLayoutId(String id) {
        this.id = id;
    }

    @Override
    public Object idHash() {
        return id;
    }
}
