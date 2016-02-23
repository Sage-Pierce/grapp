package com.wisegas.persistence.jpa.value;

import java.io.Serializable;
import java.util.UUID;

public abstract class EntityID implements Serializable {

   @Override
   public String toString() {
      return getValue();
   }

   @Override
   public boolean equals(Object object) {
      return object != null && getClass().equals(object.getClass()) && hashCode() == object.hashCode();
   }

   @Override
   public int hashCode() {
      return getValue().hashCode();
   }

   public abstract String getValue();

   protected static String generateValue() {
      return UUID.randomUUID().toString();
   }
}
