package com.wisegas.common.persistence.jpa.converter;

import com.wisegas.common.lang.value.JsonValue;

import javax.persistence.AttributeConverter;

public abstract class JsonAttributeConverter<T extends JsonValue> implements AttributeConverter<T, String> {

   @Override
   public String convertToDatabaseColumn(T attributeObject) {
      return attributeObject == null ? null : attributeObject.toString();
   }
}
