package com.wisegas.grapp.user.service.api;

import com.wisegas.grapp.user.service.dto.UserDto;

public interface UserService {
   UserDto get(String id);

   UserDto update(String id, String name);
}
