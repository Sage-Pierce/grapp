package com.wisegas.users.service_impl.factory;

import com.wisegas.users.domain.entity.User;
import com.wisegas.users.service.dto.UserDto;

public final class UserDtoFactory {

   public static UserDto createDto(User user) {
      UserDto userDto = new UserDto();
      userDto.setEmail(user.getEmail().toString());
      userDto.setName(user.getName());
      userDto.setAvatar(user.getAvatar());
      userDto.setAdmin(user.isAdmin());
      userDto.setManager(user.isManager());
      return userDto;
   }

   private UserDtoFactory() {

   }
}
