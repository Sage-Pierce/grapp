package com.wisegas.grapp.usermanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappUserListId extends EntityId {
   @Basic
   private String id;

   public static GrappUserListId generate() {
      return new GrappUserListId(generateValue());
   }

   public static GrappUserListId fromString(String string) {
      return new GrappUserListId(string);
   }

   protected GrappUserListId() {

   }

   private GrappUserListId(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
