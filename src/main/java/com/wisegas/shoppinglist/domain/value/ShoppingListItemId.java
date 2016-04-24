package com.wisegas.shoppinglist.domain.value;

import com.wisegas.common.lang.entity.AbstractId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class ShoppingListItemId extends AbstractId {
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
   public Object idHash() {
      return id;
   }
}
