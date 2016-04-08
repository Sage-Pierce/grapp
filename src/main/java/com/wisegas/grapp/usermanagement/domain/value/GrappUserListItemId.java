package com.wisegas.grapp.usermanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappUserListItemId extends EntityId {
   @Basic
   private String id;

   public static GrappUserListItemId generate() {
      return new GrappUserListItemId(generateValue());
   }

   public static GrappUserListItemId fromString(String string) {
      return new GrappUserListItemId(string);
   }

   protected GrappUserListItemId() {

   }

   private GrappUserListItemId(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
