package com.wisegas.grapp.shoppinglist.domain.value;

import com.wisegas.common.persistence.jpa.value.EntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class ShoppingListItemId extends EntityId {
   @Basic
   private String id;

   public static ShoppingListItemId generate() {
      return new ShoppingListItemId(generateValue());
   }

   public static ShoppingListItemId fromString(String string) {
      return new ShoppingListItemId(string);
   }

   protected ShoppingListItemId() {

   }

   private ShoppingListItemId(String id) {
      this.id = id;
   }

   @Override
   protected Object idHash() {
      return id;
   }
}
