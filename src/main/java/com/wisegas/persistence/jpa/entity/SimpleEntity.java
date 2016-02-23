package com.wisegas.persistence.jpa.entity;

import com.wisegas.persistence.jpa.value.DBObject;
import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SimpleEntity<T extends EntityID> extends DBObject {

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
