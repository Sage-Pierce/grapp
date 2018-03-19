package org.codegas.stores.domain.entity;

import org.codegas.commons.domain.entity.DomainEntity;
import org.codegas.commons.lang.spacial.GeoPolygon;
import org.codegas.commons.persistence.jpa.GeoPolygonConverter;
import org.codegas.stores.domain.value.FeatureId;

import javax.persistence.*;

@Entity
public class Feature extends DomainEntity<FeatureId> {

    @EmbeddedId
    private FeatureId id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private StoreLayout storeLayout;

    @Column(length = 2047)
    @Convert(converter = GeoPolygonConverter.class)
    private GeoPolygon polygon;

    public Feature(StoreLayout storeLayout, GeoPolygon polygon) {
        id = FeatureId.generate();
        setStoreLayout(storeLayout);
        setPolygon(polygon);
    }

    protected Feature() {

    }

    @Override
    public FeatureId getId() {
        return id;
    }

    public GeoPolygon getPolygon() {
        return polygon;
    }

    // Only should be called by containing StoreLayout since it maintains spacial invariants
    protected void setPolygon(GeoPolygon polygon) {
        this.polygon = polygon;
    }

    private void setStoreLayout(StoreLayout storeLayout) {
        this.storeLayout = storeLayout;
    }
}
