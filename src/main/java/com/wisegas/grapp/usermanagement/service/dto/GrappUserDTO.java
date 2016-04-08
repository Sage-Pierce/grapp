package com.wisegas.grapp.usermanagement.service.dto;

import com.wisegas.common.lang.dto.NamedDTO;

public class GrappUserDTO extends NamedDTO {

   private String email;
   private String avatar;

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getAvatar() {
      return avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }
}
