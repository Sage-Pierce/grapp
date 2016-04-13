package com.wisegas.storemanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class NodeItemId extends EntityId {
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
   protected Object idHash() {
      return id;
   }
}
