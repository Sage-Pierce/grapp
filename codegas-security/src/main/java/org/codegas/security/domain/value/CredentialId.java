package org.codegas.security.domain.value;

import javax.persistence.Embeddable;

import org.codegas.commons.lang.value.AbstractId;

@Embeddable
public class CredentialId extends AbstractId {

    private CredentialType type;

    private String provider;

    private String id;

    public static CredentialId oauth2(String provider, String id) {
        return new CredentialId(CredentialType.OAUTH2, provider, id);
    }

    protected CredentialId() {

    }

    private CredentialId(CredentialType type, String provider, String id) {
        this.type = type;
        this.provider = provider;
        this.id = id;
    }

    @Override
    public Object idHash() {
        return type + ":" + provider + ":" + id;
    }
}
