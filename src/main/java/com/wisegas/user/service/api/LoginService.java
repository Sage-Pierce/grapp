package com.wisegas.user.service.api;

import com.wisegas.common.lang.value.Email;
import com.wisegas.user.service.dto.UserDto;

public interface LoginService {
   UserDto logIn(Email email, String avatar);
}
