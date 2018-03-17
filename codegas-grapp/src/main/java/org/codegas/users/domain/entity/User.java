package org.codegas.users.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codegas.commons.lang.value.Email;
import org.codegas.commons.domain.entity.NamedEntity;

@Entity
@Table(name = "\"users\"")
public class User extends NamedEntity<Email> {

    @Id
    private String email;

    private String avatar;

    private boolean admin;

    private boolean manager;

    public User(Email email, String name, String avatar) {
        this.email = email.toString();
        setName(name);
        setAvatar(avatar);
    }

    protected User() {

    }

    @Override
    public Email getId() {
        return getEmail();
    }

    public Email getEmail() {
        return Email.fromString(email);
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isManager() {
        return manager;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
