package com.wisegas.grapp.storemanagement.service.api;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreDto;

import java.util.List;

public interface GrappStoreService {
   GrappStoreDto create(String name, GeoPoint location);

   List<GrappStoreDto> getAll();

   GrappStoreDto get(String id);

   GrappStoreDto update(String id, String name, GeoPoint location);

   void delete(String id);
}
