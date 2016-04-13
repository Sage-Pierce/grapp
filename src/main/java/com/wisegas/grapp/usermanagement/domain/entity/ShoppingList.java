package com.wisegas.grapp.usermanagement.domain.entity;

import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.usermanagement.domain.value.Item;
import com.wisegas.grapp.usermanagement.domain.value.ShoppingListId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingList extends NamedEntity<ShoppingListId> {
   @EmbeddedId
   private ShoppingListId id;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
   private User user;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shoppingList", orphanRemoval = true)
   private List<ShoppingListItem> shoppingListItems = new ArrayList<>();

   public ShoppingList(User user, String name) {
      id = ShoppingListId.generate();
      setUser(user);
      setName(name);
   }

   protected ShoppingList() {

   }

   @Override
   public ShoppingListId getId() {
      return id;
   }

   public User getUser() {
      return user;
   }

   public List<ShoppingListItem> getShoppingListItems() {
      return shoppingListItems;
   }

   public ShoppingListItem addItem(Item item) {
      ShoppingListItem shoppingListItem = new ShoppingListItem(this, item);
      shoppingListItems.add(shoppingListItem);
      return shoppingListItem;
   }

   private void setUser(User user) {
      this.user = user;
   }
}
