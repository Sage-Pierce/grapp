package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityIdFuck;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreLayoutId extends EntityIdFuck {
   @Basic
   private String id;

   public static GrappStoreLayoutId generate() {
      return new GrappStoreLayoutId(generateValue());
   }

   public static GrappStoreLayoutId fromString(String string) {
      return new GrappStoreLayoutId(string);
   }

   protected GrappStoreLayoutId() {

   }

   private GrappStoreLayoutId(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
