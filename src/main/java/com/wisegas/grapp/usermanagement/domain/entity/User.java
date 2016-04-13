package com.wisegas.grapp.usermanagement.domain.entity;

import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.usermanagement.domain.value.Email;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends NamedEntity<Email> {
   @EmbeddedId
   private Email id;

   private String avatar;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
   private List<ShoppingList> shoppingLists = new ArrayList<>();

   public User(String email, String name, String avatar) {
      id = new Email(email);
      setName(name);
      setAvatar(avatar);
   }

   protected User() {

   }

   @Override
   public Email getId() {
      return id;
   }

   public String getEmail() {
      return id.getEmail();
   }

   public String getAvatar() {
      return avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }

   public ShoppingList addShoppingList(String name) {
      ShoppingList shoppingList = new ShoppingList(this, name);
      shoppingLists.add(shoppingList);
      return shoppingList;
   }

   public List<ShoppingList> getShoppingLists() {
      return shoppingLists;
   }
}
