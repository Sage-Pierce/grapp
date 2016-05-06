package com.wisegas.storemanagement.domain.value;

import com.wisegas.common.lang.value.AbstractId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class StoreLayoutId extends AbstractId {
   @Basic
   private String id;

   public static StoreLayoutId generate() {
      return new StoreLayoutId(generateValue());
   }

   public static StoreLayoutId fromString(String string) {
      return new StoreLayoutId(string);
   }

   protected StoreLayoutId() {

   }

   private StoreLayoutId(String id) {
      this.id = id;
   }

   @Override
   public Object idHash() {
      return id;
   }
}
