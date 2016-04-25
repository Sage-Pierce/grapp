package com.wisegas.user.service.api;

import com.wisegas.common.lang.value.Email;
import com.wisegas.user.service.dto.UserDto;

public interface UserService {
   UserDto get(Email email);

   UserDto update(Email email, String name);
}
