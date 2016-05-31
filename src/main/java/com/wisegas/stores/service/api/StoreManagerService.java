package com.wisegas.stores.service.api;

import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.common.lang.value.Email;
import com.wisegas.stores.service.dto.StoreDto;
import com.wisegas.stores.service.dto.StoreManagerDto;

public interface StoreManagerService {
   StoreManagerDto loadByEmail(Email email);

   StoreDto addStore(Email email, String name, GeoPoint location);
}
