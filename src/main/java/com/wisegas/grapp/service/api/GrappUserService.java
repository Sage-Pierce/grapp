package com.wisegas.grapp.service.api;

import com.wisegas.grapp.service.dto.GrappUserDTO;

public interface GrappUserService {
   GrappUserDTO get(String id);

   GrappUserDTO update(String id, String name);
}
