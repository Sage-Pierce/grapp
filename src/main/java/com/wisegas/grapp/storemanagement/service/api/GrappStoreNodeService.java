package com.wisegas.grapp.storemanagement.service.api;

import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeDTO;

public interface GrappStoreNodeService {
   GrappStoreNodeDTO get(String id);

   GrappStoreNodeDTO update(String id, String name);
}
