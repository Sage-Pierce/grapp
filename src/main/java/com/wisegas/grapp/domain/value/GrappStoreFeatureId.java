package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GrappStoreFeatureId extends EntityId implements Serializable {
   @Basic
   private String id;

   public static GrappStoreFeatureId generate() {
      return new GrappStoreFeatureId(generateValue());
   }

   public static GrappStoreFeatureId fromString(String string) {
      return new GrappStoreFeatureId(string);
   }

   protected GrappStoreFeatureId() {

   }

   private GrappStoreFeatureId(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
