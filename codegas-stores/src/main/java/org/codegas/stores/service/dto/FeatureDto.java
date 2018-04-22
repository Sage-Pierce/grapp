package org.codegas.stores.service.dto;

import org.codegas.commons.lang.spacial.GeoPolygon;
import org.codegas.commons.lang.value.AbstractDto;

public class FeatureDto extends AbstractDto {

    private GeoPolygon polygon;

    public GeoPolygon getPolygon() {
        return polygon;
    }

    public void setPolygon(GeoPolygon polygon) {
        this.polygon = polygon;
    }
}
