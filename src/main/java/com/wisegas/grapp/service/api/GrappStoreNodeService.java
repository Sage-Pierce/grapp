package com.wisegas.grapp.service.api;

import com.wisegas.grapp.service.dto.GrappStoreNodeDTO;

public interface GrappStoreNodeService {
   GrappStoreNodeDTO findByID(String id);
}
