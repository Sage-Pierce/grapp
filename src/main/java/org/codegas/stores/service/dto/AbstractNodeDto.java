package org.codegas.stores.service.dto;

import org.codegas.common.lang.spacial.GeoPoint;
import org.codegas.common.lang.value.AbstractDto;

public abstract class AbstractNodeDto extends AbstractDto {

    private String name;

    private String type;

    private GeoPoint location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }
}
