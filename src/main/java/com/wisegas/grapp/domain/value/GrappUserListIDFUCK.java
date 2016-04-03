package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappUserListIDFUCK extends EntityID {
   @Basic
   private String id;

   public static GrappUserListIDFUCK generate() {
      return new GrappUserListIDFUCK(generateValue());
   }

   public static GrappUserListIDFUCK fromString(String string) {
      return new GrappUserListIDFUCK(string);
   }

   protected GrappUserListIDFUCK() {

   }

   private GrappUserListIDFUCK(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
