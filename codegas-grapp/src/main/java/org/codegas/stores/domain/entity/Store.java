package org.codegas.stores.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.codegas.commons.lang.spacial.GeoPoint;
import org.codegas.commons.persistence.jpa.GeoPointConverter;
import org.codegas.commons.domain.entity.NamedEntity;
import org.codegas.stores.domain.value.StoreId;

@Entity
public class Store extends NamedEntity<StoreId> {

    @EmbeddedId
    private StoreId id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private StoreManager manager;

    @Column(length = 63)
    @Convert(converter = GeoPointConverter.class)
    private GeoPoint location;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "store", orphanRemoval = true)
    private StoreLayout storeLayout = new StoreLayout(this);

    public Store(StoreManager manager, String name, GeoPoint location) {
        id = StoreId.generate();
        setManager(manager);
        setName(name);
        setLocation(location);
    }

    protected Store() {

    }

    @Override
    public StoreId getId() {
        return id;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public StoreLayout getStoreLayout() {
        return storeLayout;
    }

    private void setManager(StoreManager manager) {
        this.manager = manager;
    }
}
