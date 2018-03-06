package org.codegas.stores.domain.value;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import org.codegas.commons.lang.value.AbstractId;

@Embeddable
public class NodeItemId extends AbstractId {

    @Basic
    private String id;

    public static NodeItemId generate() {
        return new NodeItemId(generateValue());
    }

    public static NodeItemId fromString(String string) {
        return new NodeItemId(string);
    }

    protected NodeItemId() {

    }

    private NodeItemId(String id) {
        this.id = id;
    }

    @Override
    public Object idHash() {
        return id;
    }
}
