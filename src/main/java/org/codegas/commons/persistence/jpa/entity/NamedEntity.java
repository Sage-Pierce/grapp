package org.codegas.commons.persistence.jpa.entity;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;

import org.codegas.commons.domain.entity.SimpleEntity;
import org.codegas.commons.lang.value.Id;

@MappedSuperclass
public abstract class NamedEntity<T extends Id> extends SimpleEntity<T> {

    @Basic(optional = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || (name = name.trim()).isEmpty()) {
            throw new IllegalArgumentException("Entity name must not be null/empty: " + name);
        }
        this.name = name;
    }
}
