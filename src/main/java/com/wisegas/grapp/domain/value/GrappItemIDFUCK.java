package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GrappItemIDFUCK extends EntityID {
   @Basic
   private String id;

   public static GrappItemIDFUCK generate() {
      return new GrappItemIDFUCK(generateValue());
   }

   public static GrappItemIDFUCK fromString(String string) {
      return new GrappItemIDFUCK(string);
   }

   protected GrappItemIDFUCK() {

   }

   private GrappItemIDFUCK(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
