package org.codegas.stores.service.dto;

import java.util.List;

import org.codegas.commons.lang.spacial.GeoPolygon;
import org.codegas.commons.lang.value.AbstractDto;

public abstract class LayoutDto extends AbstractDto {

    private GeoPolygon outerOutline;

    private GeoPolygon innerOutline;

    private List<FeatureDto> features;

    public GeoPolygon getOuterOutline() {
        return outerOutline;
    }

    public void setOuterOutline(GeoPolygon outerOutline) {
        this.outerOutline = outerOutline;
    }

    public GeoPolygon getInnerOutline() {
        return innerOutline;
    }

    public void setInnerOutline(GeoPolygon innerOutline) {
        this.innerOutline = innerOutline;
    }

    public List<FeatureDto> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureDto> features) {
        this.features = features;
    }
}
