package com.wisegas.user.service.dto;

import com.wisegas.common.lang.value.Email;

public class UserDto {

   private Email email;
   private String name;
   private String avatar;

   public Email getEmail() {
      return email;
   }

   public void setEmail(Email email) {
      this.email = email;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAvatar() {
      return avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }
}
