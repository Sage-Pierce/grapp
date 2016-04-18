package com.wisegas.storemanagement.service.api;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.storemanagement.service.dto.StoreDto;

import java.util.List;

public interface StoreService {
   List<StoreDto> getAll();

   StoreDto get(String id);

   StoreDto update(String id, String name, GeoPoint location);

   void delete(String id);
}
