package com.wisegas.grapp.usermanagement.service.api;

import com.wisegas.grapp.usermanagement.service.dto.GrappUserDTOO;

public interface GrappLoginService {
   GrappUserDTOO logIn(String email, String avatar);
}
