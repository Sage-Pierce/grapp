package com.wisegas.grapp.usermanagement.service.api;

import com.wisegas.grapp.usermanagement.service.dto.GrappUserDTO;

public interface GrappLoginService {
   GrappUserDTO logIn(String email, String avatar);
}
