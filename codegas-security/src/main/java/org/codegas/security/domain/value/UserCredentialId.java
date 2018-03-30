package org.codegas.security.domain.value;

import javax.persistence.Basic;

import org.codegas.commons.lang.value.AbstractId;

public class UserCredentialId extends AbstractId {

    @Basic
    private String id;

    public static UserCredentialId generate() {
        return new UserCredentialId(generateValue());
    }

    protected UserCredentialId() {

    }

    private UserCredentialId(String id) {
        this.id = id;
    }

    @Override
    public Object idHash() {
        return id;
    }

}
