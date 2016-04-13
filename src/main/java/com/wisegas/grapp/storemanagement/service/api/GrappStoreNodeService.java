package com.wisegas.grapp.storemanagement.service.api;

import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeDTOO;

public interface GrappStoreNodeService {
   GrappStoreNodeDTOO get(String id);

   GrappStoreNodeDTOO update(String id, String name);
}
