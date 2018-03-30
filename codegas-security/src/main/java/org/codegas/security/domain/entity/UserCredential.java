package org.codegas.security.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.codegas.commons.domain.entity.DomainEntity;
import org.codegas.security.domain.value.UserCredentialId;

@Entity
public class UserCredential extends DomainEntity<UserCredentialId> {

    @EmbeddedId
    private UserCredentialId id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private User user;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private Credential credential;

    public UserCredential(User user, Credential credential) {
        this.id = UserCredentialId.generate();
        this.user = user;
        this.credential = credential;
    }

    protected UserCredential() {

    }

    public UserCredential refreshCredential(Credential refreshedCredential) {
        credential.apply(refreshedCredential.getAccessToken(), refreshedCredential.getRefreshToken(), refreshedCredential.getExpiration());
        return this;
    }

    public void expire() {
        credential.expire();
    }

    @Override
    public UserCredentialId getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Credential getCredential() {
        return credential;
    }
}
