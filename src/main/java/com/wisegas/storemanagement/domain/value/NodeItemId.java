package com.wisegas.storemanagement.domain.value;

import com.wisegas.common.lang.value.AbstractId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class NodeItemId extends AbstractId {
   @Basic
   private String id;

   public static NodeItemId generate() {
      return new NodeItemId(generateValue());
   }

   public static NodeItemId fromString(String string) {
      return new NodeItemId(string);
   }

   protected NodeItemId() {

   }

   private NodeItemId(String id) {
      this.id = id;
   }

   @Override
   public Object idHash() {
      return id;
   }
}
