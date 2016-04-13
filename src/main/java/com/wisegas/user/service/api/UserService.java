package com.wisegas.user.service.api;

import com.wisegas.user.service.dto.UserDto;

public interface UserService {
   UserDto get(String id);

   UserDto update(String id, String name);
}
