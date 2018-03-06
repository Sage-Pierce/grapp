package org.codegas.stores.service.dto;

import org.codegas.common.lang.spacial.GeoPoint;
import org.codegas.common.lang.value.AbstractDto;

public class StoreDto extends AbstractDto {

    private String name;

    private GeoPoint location;

    private String layoutId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }
}
