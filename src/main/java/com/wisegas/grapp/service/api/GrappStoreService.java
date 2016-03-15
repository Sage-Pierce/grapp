package com.wisegas.grapp.service.api;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.grapp.service.dto.GrappStoreDTO;

import java.util.List;

public interface GrappStoreService {
   GrappStoreDTO create(String name, GeoPoint location);

   List<GrappStoreDTO> getAll();

   GrappStoreDTO get(String id);

   GrappStoreDTO update(String id, String name, GeoPoint location);

   void delete(String id);
}
