package com.wisegas.storemanagement.domain.value;

import com.wisegas.common.lang.entity.AbstractEntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class LayoutId extends AbstractEntityId {
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
   public Object idHash() {
      return id;
   }
}
