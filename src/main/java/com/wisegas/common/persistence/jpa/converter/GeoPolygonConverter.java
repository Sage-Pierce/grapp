package com.wisegas.common.persistence.jpa.converter;

import com.wisegas.common.lang.spacial.GeoPolygon;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
