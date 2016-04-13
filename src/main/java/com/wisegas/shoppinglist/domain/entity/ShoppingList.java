package com.wisegas.shoppinglist.domain.entity;

import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.shoppinglist.domain.value.Item;
import com.wisegas.shoppinglist.domain.value.ShoppingListId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingList extends NamedEntity<ShoppingListId> {
   @EmbeddedId
   private ShoppingListId id;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shoppingList", orphanRemoval = true)
   private List<ShoppingListItem> shoppingListItems = new ArrayList<>();

   public ShoppingList(String name) {
      id = ShoppingListId.generate();
      setName(name);
   }

   protected ShoppingList() {

   }

   @Override
   public ShoppingListId getId() {
      return id;
   }

   public List<ShoppingListItem> getShoppingListItems() {
      return shoppingListItems;
   }

   public ShoppingListItem addItem(Item item) {
      ShoppingListItem shoppingListItem = new ShoppingListItem(this, item);
      shoppingListItems.add(shoppingListItem);
      return shoppingListItem;
   }
}
