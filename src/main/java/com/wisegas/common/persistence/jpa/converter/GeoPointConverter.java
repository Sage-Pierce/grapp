package com.wisegas.common.persistence.jpa.converter;

import com.wisegas.common.lang.spacial.GeoPoint;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
