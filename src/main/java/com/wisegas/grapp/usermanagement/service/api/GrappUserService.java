package com.wisegas.grapp.usermanagement.service.api;

import com.wisegas.grapp.usermanagement.service.dto.GrappUserDTO;

public interface GrappUserService {
   GrappUserDTO get(String id);

   GrappUserDTO update(String id, String name);
}
