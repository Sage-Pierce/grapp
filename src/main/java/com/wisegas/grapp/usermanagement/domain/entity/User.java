package com.wisegas.grapp.usermanagement.domain.entity;

import com.wisegas.common.persistence.jpa.entity.NamedEntity;
import com.wisegas.grapp.usermanagement.domain.value.Email;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class User extends NamedEntity<Email> {
   @EmbeddedId
   private Email id;

   private String avatar;

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
}
