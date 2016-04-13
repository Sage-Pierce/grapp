package com.wisegas.grapp.usermanagement.service.api;

import com.wisegas.grapp.usermanagement.service.dto.GrappUserDto;

public interface GrappLoginService {
   GrappUserDto logIn(String email, String avatar);
}
