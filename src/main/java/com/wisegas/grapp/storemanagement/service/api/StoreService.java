package com.wisegas.grapp.storemanagement.service.api;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.grapp.storemanagement.service.dto.StoreDto;

import java.util.List;

public interface StoreService {
   StoreDto create(String name, GeoPoint location);

   List<StoreDto> getAll();

   StoreDto get(String id);

   StoreDto update(String id, String name, GeoPoint location);

   void delete(String id);
}
