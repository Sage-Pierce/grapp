package com.wisegas.common.persistence.jpa.entity;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class NamedEntity<T extends EntityID> extends SimpleEntity<T> {
   @Basic(optional = false)
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
