package com.wisegas.user.domain.entity;

import com.wisegas.common.lang.value.Email;
import com.wisegas.common.persistence.jpa.entity.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"users\"")
public class User extends NamedEntity<Email> {
   @Id
   private String email;

   private String avatar;

   public User(Email email, String name, String avatar) {
      this.email = email.toString();
      setName(name);
      setAvatar(avatar);
   }

   protected User() {

   }

   @Override
   public Email getId() {
      return Email.fromString(email);
   }

   public String getAvatar() {
      return avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }
}
