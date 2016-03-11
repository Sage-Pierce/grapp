package com.wisegas.common.persistence.jpa.converter;

import com.wisegas.common.lang.value.GeoPolygon;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GeoPolygonConverter extends JsonAttributeConverter<GeoPolygon> implements AttributeConverter<GeoPolygon, String> {

   @Override
   public String convertToDatabaseColumn(GeoPolygon attributeObject) {
      return super.convertToDatabaseColumn(attributeObject);
   }

   @Override
   public GeoPolygon convertToEntityAttribute(String dbData) {
      return GeoPolygon.fromString(dbData);
   }
}
