package com.wisegas.grapp.usermanagement.domain.entity;

import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.grapp.usermanagement.domain.value.Item;
import com.wisegas.grapp.usermanagement.domain.value.ShoppingListItemId;

import javax.persistence.*;

@Entity
public class ShoppingListItem extends SimpleEntity<ShoppingListItemId> {
   @EmbeddedId
   private ShoppingListItemId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private ShoppingList shoppingList;

   private Item item;

   public ShoppingListItem(ShoppingList shoppingList, Item item) {
      id = ShoppingListItemId.generate();
      setShoppingList(shoppingList);
      setItem(item);
   }

   protected ShoppingListItem() {

   }

   @Override
   public ShoppingListItemId getId() {
      return id;
   }

   public Item getItem() {
      return item;
   }

   private void setShoppingList(ShoppingList shoppingList) {
      this.shoppingList = shoppingList;
   }

   private void setItem(Item item) {
      this.item = item;
   }
}
