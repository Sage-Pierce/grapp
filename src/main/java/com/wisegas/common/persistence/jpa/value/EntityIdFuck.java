package com.wisegas.common.persistence.jpa.value;

import java.io.Serializable;
import java.util.UUID;

public abstract class EntityIdFuck implements Serializable {

   @Override
   public boolean equals(Object object) {
      return object != null && getClass().equals(object.getClass()) && hashCode() == object.hashCode();
   }

   @Override
   public int hashCode() {
      return idHash().hashCode();
   }

   @Override
   public String toString() {
      return idHash().toString();
   }

   protected static String generateValue() {
      return UUID.randomUUID().toString();
   }

   protected abstract Object idHash();
}
