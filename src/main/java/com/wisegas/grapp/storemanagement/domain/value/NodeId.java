package com.wisegas.grapp.storemanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class NodeId extends EntityId {
   @Basic
   private String id;

   public static NodeId generate() {
      return new NodeId(generateValue());
   }

   public static NodeId fromString(String string) {
      return new NodeId(string);
   }

   protected NodeId() {

   }

   private NodeId(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
