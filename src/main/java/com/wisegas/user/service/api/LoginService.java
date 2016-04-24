package com.wisegas.user.service.api;

import com.wisegas.user.service.dto.UserDto;

public interface LoginService {
   UserDto logIn(String emailString, String avatar);
}
