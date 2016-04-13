package com.wisegas.storemanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class LayoutId extends EntityId {
   @Basic
   private String id;

   public static LayoutId generate() {
      return new LayoutId(generateValue());
   }

   public static LayoutId fromString(String string) {
      return new LayoutId(string);
   }

   protected LayoutId() {

   }

   private LayoutId(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
