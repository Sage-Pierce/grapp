package com.wisegas.grapp.usermanagement.service.api;

import com.wisegas.grapp.usermanagement.service.dto.GrappUserDTOO;

public interface GrappUserService {
   GrappUserDTOO get(String id);

   GrappUserDTOO update(String id, String name);
}
