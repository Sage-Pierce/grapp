package org.codegas.security.domain.entity;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;

import org.codegas.commons.domain.entity.DomainEntity;
import org.codegas.security.domain.value.UserAttributeType;
import org.codegas.security.domain.value.UserId;

@Entity(name = "secureuser")
public class User extends DomainEntity<UserId> implements Principal {

    @EmbeddedId
    private UserId id;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<UserAttributeType, String> attributes = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private List<UserCredential> credentials = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    public User(UserId id) {
        this.id = id;
    }

    protected User() {

    }

    public boolean setAttribute(UserAttributeType type, String value) {
        return value != null && !Objects.equals(attributes.put(type, value), value);
    }

    public UserCredential refreshCredential(Credential credential) {
        return credentials.stream()
            .filter(userCredential -> Objects.equals(credential, userCredential.getCredential()))
            .findFirst()
            .map(userCredential -> userCredential.refreshCredential(credential))
            .orElseGet(() -> addCredential(credential));
    }

    public boolean isInRole(String role) {
        return roles.contains(role);
    }

    @Override
    public UserId getId() {
        return id;
    }

    @Override
    public String getName() {
        return attributes.getOrDefault(UserAttributeType.EMAIL, id.toString());
    }

    private UserCredential addCredential(Credential credential) {
        UserCredential userCredential = new UserCredential(this, credential);
        credentials.add(userCredential);
        return userCredential;
    }
}
