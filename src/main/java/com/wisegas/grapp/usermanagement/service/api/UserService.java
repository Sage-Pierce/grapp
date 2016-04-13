package com.wisegas.grapp.usermanagement.service.api;

import com.wisegas.grapp.usermanagement.service.dto.UserDto;

public interface UserService {
   UserDto get(String id);

   UserDto update(String id, String name);
}
