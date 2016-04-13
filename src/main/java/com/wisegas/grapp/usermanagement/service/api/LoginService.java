package com.wisegas.grapp.usermanagement.service.api;

import com.wisegas.grapp.usermanagement.service.dto.UserDto;

public interface LoginService {
   UserDto logIn(String email, String avatar);
}
