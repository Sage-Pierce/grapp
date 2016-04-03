package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappStoreNodeItemIDFUCK extends EntityID {
   @Basic
   private String id;

   public static GrappStoreNodeItemIDFUCK generate() {
      return new GrappStoreNodeItemIDFUCK(generateValue());
   }

   public static GrappStoreNodeItemIDFUCK fromString(String string) {
      return new GrappStoreNodeItemIDFUCK(string);
   }

   protected GrappStoreNodeItemIDFUCK() {

   }

   private GrappStoreNodeItemIDFUCK(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
