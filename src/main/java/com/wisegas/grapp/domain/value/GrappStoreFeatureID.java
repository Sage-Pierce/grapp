package com.wisegas.grapp.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityID;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GrappStoreFeatureID extends EntityID implements Serializable {
   @Basic
   private String id;

   public static GrappStoreFeatureID generate() {
      return new GrappStoreFeatureID(generateValue());
   }

   public static GrappStoreFeatureID fromString(String string) {
      return new GrappStoreFeatureID(string);
   }

   protected GrappStoreFeatureID() {

   }

   private GrappStoreFeatureID(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
