package com.wisegas.grapp.user.service.api;

import com.wisegas.grapp.user.service.dto.UserDto;

public interface LoginService {
   UserDto logIn(String email, String avatar);
}
