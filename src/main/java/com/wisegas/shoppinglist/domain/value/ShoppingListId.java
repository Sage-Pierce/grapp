package com.wisegas.shoppinglist.domain.value;

import com.wisegas.common.lang.entity.AbstractEntityId;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class ShoppingListId extends AbstractEntityId {
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
   public Object idHash() {
      return id;
   }
}
