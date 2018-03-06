package org.codegas.common.persistence.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.codegas.common.lang.spacial.GeoPoint;

@Converter
public class GeoPointConverter implements AttributeConverter<GeoPoint, String> {

    @Override
    public String convertToDatabaseColumn(GeoPoint attributeObject) {
        return attributeObject == null ? null : attributeObject.toString();
    }

    @Override
    public GeoPoint convertToEntityAttribute(String dbData) {
        return GeoPoint.fromString(dbData);
    }
}
