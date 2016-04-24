package com.wisegas.common.lang.entity;

import com.wisegas.common.lang.value.Id;

public abstract class SimpleEntity<T extends Id> {

   @Override
   public boolean equals(Object object) {
      return object != null && getClass().equals(object.getClass()) && hashCode() == object.hashCode();
   }

   @Override
   public int hashCode() {
      return getId().hashCode();
   }

   public abstract T getId();
}
