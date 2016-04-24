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

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private Shopper shopper;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shoppingList", orphanRemoval = true)
   private List<ShoppingListItem> items = new ArrayList<>();

   public ShoppingList(Shopper shopper, String name) {
      id = ShoppingListId.generate();
      setName(name);
      setShopper(shopper);
   }

   protected ShoppingList() {

   }

   @Override
   public ShoppingListId getId() {
      return id;
   }

   public List<ShoppingListItem> getItems() {
      return items;
   }

   public ShoppingListItem addItem(Item item) {
      ShoppingListItem shoppingListItem = new ShoppingListItem(this, item);
      items.add(shoppingListItem);
      return shoppingListItem;
   }

   private void setShopper(Shopper shopper) {
      this.shopper = shopper;
   }
}
