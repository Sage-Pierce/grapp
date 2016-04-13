package com.wisegas.grapp.usermanagement.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class ShoppingListId extends EntityId {
   @Basic
   private String id;

   public static ShoppingListId generate() {
      return new ShoppingListId(generateValue());
   }

   public static ShoppingListId fromString(String string) {
      return new ShoppingListId(string);
   }

   protected ShoppingListId() {

   }

   private ShoppingListId(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
