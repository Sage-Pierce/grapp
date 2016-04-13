package com.wisegas.grapp.user.service_impl.factory;

import com.wisegas.grapp.user.domain.entity.User;
import com.wisegas.grapp.user.service.dto.UserDto;

public final class UserDtoFactory {

   public static UserDto createDto(User user) {
      UserDto userDto = new UserDto();
      userDto.setId(user.getId().toString());
      userDto.setName(user.getName());
      userDto.setAvatar(user.getAvatar());
      return userDto;
   }

   private UserDtoFactory() {

   }
}
