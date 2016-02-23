package com.wisegas.grapp.service.api;

import com.wisegas.grapp.service.dto.GrappUserDTO;

public interface GrappUserService {
   GrappUserDTO findByID(String id);

   GrappUserDTO updateName(String id, String name);
}
