package com.wisegas.users.service.dto;

public class UserDto {

   private String email;
   private String name;
   private String avatar;
   private boolean admin;
   private boolean manager;

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
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

   public boolean isManager() {
      return manager;
   }

   public void setManager(boolean manager) {
      this.manager = manager;
   }

   public boolean isAdmin() {
      return admin;
   }

   public void setAdmin(boolean admin) {
      this.admin = admin;
   }
}
