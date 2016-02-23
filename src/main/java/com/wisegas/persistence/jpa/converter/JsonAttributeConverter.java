package com.wisegas.persistence.jpa.converter;

import com.wisegas.value.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public abstract class JsonAttributeConverter<T extends JsonValue> implements AttributeConverter<T, String> {

   @Override
   public String convertToDatabaseColumn(T attributeObject) {
      return attributeObject == null ? null : attributeObject.toString();
   }
}
