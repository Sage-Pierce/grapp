package com.wisegas.users.service.api;

import com.wisegas.common.lang.value.Email;
import com.wisegas.users.service.dto.UserDto;

public interface LoginService {
   UserDto logIn(Email email, String avatar);
}
