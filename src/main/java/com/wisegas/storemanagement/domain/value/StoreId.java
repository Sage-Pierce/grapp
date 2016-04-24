package com.wisegas.storemanagement.domain.value;

import com.wisegas.common.lang.value.AbstractId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class StoreId extends AbstractId {
   @Basic
   private String id;

   public static StoreId generate() {
      return new StoreId(generateValue());
   }

   public static StoreId fromString(String string) {
      return new StoreId(string);
   }

   protected StoreId() {

   }

   private StoreId(String id) {
      this.id = id;
   }

   @Override
   public Object idHash() {
      return id;
   }
}
