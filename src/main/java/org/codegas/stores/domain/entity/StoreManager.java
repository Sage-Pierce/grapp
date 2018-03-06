package org.codegas.stores.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codegas.common.domain.entity.SimpleEntity;
import org.codegas.common.lang.spacial.GeoPoint;
import org.codegas.common.lang.value.Email;

@Entity
public class StoreManager extends SimpleEntity<Email> {

    @Id
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "manager", orphanRemoval = true)
    private List<Store> stores = new ArrayList<>();

    public StoreManager(Email email) {
        this.email = email.toString();
    }

    protected StoreManager() {

    }

    @Override
    public Email getId() {
        return getEmail();
    }

    public Email getEmail() {
        return Email.fromString(email);
    }

    public List<Store> getStores() {
        return stores;
    }

    public Store addStore(String name, GeoPoint location) {
        Store store = new Store(this, name, location);
        stores.add(store);
        return store;
    }
}
