package com.wisegas.grapp.user.service.dto;

import com.wisegas.common.lang.dto.NamedDto;

public class UserDto extends NamedDto {

   private String avatar;

   public String getAvatar() {
      return avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }
}
