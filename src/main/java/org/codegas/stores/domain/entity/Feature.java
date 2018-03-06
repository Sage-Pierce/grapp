package org.codegas.stores.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.codegas.common.domain.entity.SimpleEntity;
import org.codegas.common.lang.spacial.GeoPolygon;
import org.codegas.common.persistence.jpa.converter.GeoPolygonConverter;
import org.codegas.stores.domain.value.FeatureId;

@Entity
public class Feature extends SimpleEntity<FeatureId> {

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
