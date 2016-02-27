package com.wisegas.grapp.domain.value;

import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GrappStoreLayoutFeatureID extends EntityID implements Serializable {
   @Basic
   private String id;

   public static GrappStoreLayoutFeatureID generate() {
      return new GrappStoreLayoutFeatureID(generateValue());
   }

   public static GrappStoreLayoutFeatureID fromString(String string) {
      return new GrappStoreLayoutFeatureID(string);
   }

   protected GrappStoreLayoutFeatureID() {

   }

   private GrappStoreLayoutFeatureID(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
