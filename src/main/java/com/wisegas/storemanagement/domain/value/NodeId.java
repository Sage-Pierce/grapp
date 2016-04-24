package com.wisegas.storemanagement.domain.value;

import com.wisegas.common.lang.value.AbstractId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class NodeId extends AbstractId {
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
   public Object idHash() {
      return id;
   }
}
