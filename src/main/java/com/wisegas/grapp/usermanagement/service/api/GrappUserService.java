package com.wisegas.grapp.usermanagement.service.api;

import com.wisegas.grapp.usermanagement.service.dto.GrappUserDto;

public interface GrappUserService {
   GrappUserDto get(String id);

   GrappUserDto update(String id, String name);
}
