package com.wisegas.stores.service.api;

import com.wisegas.common.lang.spacial.GeoPoint;
import com.wisegas.stores.service.dto.StoreDto;

import java.util.List;

public interface StoreService {
   List<StoreDto> get();

   StoreDto get(String id);

   StoreDto update(String id, String name, GeoPoint location);

   void delete(String id);
}
