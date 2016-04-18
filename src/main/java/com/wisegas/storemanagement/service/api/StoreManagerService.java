package com.wisegas.storemanagement.service.api;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.storemanagement.service.dto.StoreDto;
import com.wisegas.storemanagement.service.dto.StoreManagerDto;

public interface StoreManagerService {
   StoreManagerDto loadByEmail(String email);

   StoreDto createStore(String id, String name, GeoPoint location);
}
