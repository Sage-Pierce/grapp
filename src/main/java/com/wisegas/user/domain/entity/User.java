package com.wisegas.user.domain.entity;

import com.wisegas.common.lang.jpa.Email;
import com.wisegas.common.persistence.jpa.entity.NamedEntity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"users\"")
public class User extends NamedEntity<Email> {
   @EmbeddedId
   private Email id;

   private String avatar;

   public User(Email email, String name, String avatar) {
      id = email;
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
