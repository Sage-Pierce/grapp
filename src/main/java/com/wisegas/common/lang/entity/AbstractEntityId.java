package com.wisegas.common.lang.entity;

import java.util.UUID;

public abstract class AbstractEntityId implements EntityId {

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
}
