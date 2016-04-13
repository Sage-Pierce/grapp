package com.wisegas.grapp.storemanagement.service.api;

import com.wisegas.grapp.storemanagement.service.dto.GrappStoreNodeDto;

public interface GrappStoreNodeService {
   GrappStoreNodeDto get(String id);

   GrappStoreNodeDto update(String id, String name);
}
