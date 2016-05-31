package com.wisegas.shoppinglists.domain.entity;

import com.wisegas.common.lang.entity.SimpleEntity;
import com.wisegas.common.lang.value.Email;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shopper extends SimpleEntity<Email> {
   @Id
   private String email;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shopper", orphanRemoval = true)
   private List<ShoppingList> lists = new ArrayList<>();

   public Shopper(Email email) {
      this.email = email.toString();
   }

   protected Shopper() {

   }

   @Override
   public Email getId() {
      return getEmail();
   }

   public Email getEmail() {
      return Email.fromString(email);
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
