package com.wisegas.shoppinglist.domain.entity;

import com.wisegas.common.lang.jpa.Email;
import com.wisegas.common.persistence.jpa.entity.SimpleEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shopper extends SimpleEntity<Email> {
   @EmbeddedId
   private Email id;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shopper", orphanRemoval = true)
   private List<ShoppingList> lists = new ArrayList<>();

   public Shopper(String email) {
      id = new Email(email);
   }

   protected Shopper() {

   }

   @Override
   public Email getId() {
      return id;
   }

   public List<ShoppingList> getLists() {
      return lists;
   }

   public ShoppingList addList(String name) {
      ShoppingList list = new ShoppingList(this, name);
      lists.add(list);
      return list;
   }
}
