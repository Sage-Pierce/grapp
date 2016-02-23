package com.wisegas.grapp.domain.value;

import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GrappStoreLayoutFeatureID extends EntityID implements Serializable {
   @Basic
   private String value;

   public static GrappStoreLayoutFeatureID generate() {
      return new GrappStoreLayoutFeatureID(generateValue());
   }

   public static GrappStoreLayoutFeatureID fromString(String string) {
      return new GrappStoreLayoutFeatureID(string);
   }

   protected GrappStoreLayoutFeatureID() {

   }

   private GrappStoreLayoutFeatureID(String value) {
      this.value = value;
   }

   @Override
   public String getValue() {
      return value;
   }
}
