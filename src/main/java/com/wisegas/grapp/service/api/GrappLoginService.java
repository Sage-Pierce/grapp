package com.wisegas.grapp.service.api;

import com.wisegas.grapp.service.dto.GrappUserDTO;

public interface GrappLoginService {
   GrappUserDTO logIn(String email, String avatar);
}
