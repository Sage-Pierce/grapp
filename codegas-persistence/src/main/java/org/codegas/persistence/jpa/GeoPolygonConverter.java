package org.codegas.persistence.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.codegas.commons.lang.spacial.GeoPolygon;

@Converter
public class GeoPolygonConverter implements AttributeConverter<GeoPolygon, String> {

    @Override
    public String convertToDatabaseColumn(GeoPolygon attributeObject) {
        return attributeObject == null ? null : attributeObject.toString();
    }

    @Override
    public GeoPolygon convertToEntityAttribute(String dbData) {
        return GeoPolygon.fromString(dbData);
    }
}
