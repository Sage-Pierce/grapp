package org.codegas.stores.domain.entity;

import org.codegas.commons.domain.entity.DomainEntity;
import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.commons.lang.value.PrincipalName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StoreManager extends DomainEntity<PrincipalName> {

    @Id
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "manager", orphanRemoval = true)
    private List<Store> stores = new ArrayList<>();

    public StoreManager(PrincipalName name) {
        this.name = name.toString();
    }

    protected StoreManager() {

    }

    @Override
    public PrincipalName getId() {
        return getName();
    }

    public PrincipalName getName() {
        return PrincipalName.fromString(name);
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
