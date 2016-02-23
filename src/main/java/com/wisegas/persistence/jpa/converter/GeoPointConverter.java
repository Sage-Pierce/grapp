package com.wisegas.persistence.jpa.converter;

import com.wisegas.value.GeoPoint;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GeoPointConverter extends JsonAttributeConverter<GeoPoint> implements AttributeConverter<GeoPoint, String> {

   @Override
   public String convertToDatabaseColumn(GeoPoint attributeObject) {
      return super.convertToDatabaseColumn(attributeObject);
   }

   @Override
   public GeoPoint convertToEntityAttribute(String dbData) {
      return GeoPoint.fromString(dbData);
   }
}
