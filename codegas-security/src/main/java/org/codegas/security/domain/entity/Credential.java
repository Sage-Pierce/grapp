package org.codegas.security.domain.entity;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.codegas.commons.domain.entity.DomainEntity;
import org.codegas.security.domain.value.CredentialId;

@Entity
public class Credential extends DomainEntity<CredentialId> {

    @EmbeddedId
    private CredentialId id;

    @Column(unique = true, nullable = false)
    private String accessToken;

    private String refreshToken;

    private OffsetDateTime expiration;

    private OffsetDateTime created;

    private OffsetDateTime updated;

    public Credential(CredentialId id) {
        this.id = id;
        this.created = OffsetDateTime.now();
    }

    protected Credential() {

    }

    public Credential apply(String accessToken, String refreshToken, OffsetDateTime expiration) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
        this.updated = OffsetDateTime.now();
        return this;
    }

    public boolean isFresh() {
        return expiration.isAfter(OffsetDateTime.now());
    }

    public void expire() {
        this.expiration = OffsetDateTime.MIN;
    }

    @Override
    public CredentialId getId() {
        return id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public OffsetDateTime getExpiration() {
        return expiration;
    }
}
