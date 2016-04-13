package com.wisegas.user.service_impl.factory;

import com.wisegas.user.domain.entity.User;
import com.wisegas.user.service.dto.UserDto;

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
