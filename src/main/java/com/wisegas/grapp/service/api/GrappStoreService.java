package com.wisegas.grapp.service.api;

import com.wisegas.grapp.service.dto.GrappStoreDTO;
import com.wisegas.common.lang.value.GeoPoint;

import java.util.List;

public interface GrappStoreService {
   GrappStoreDTO create(String name, GeoPoint location);

   List<GrappStoreDTO> findAll();

   GrappStoreDTO findByID(String id);

   GrappStoreDTO updateName(String id, String name);

   GrappStoreDTO updateLocation(String id, GeoPoint location);

   void deleteByID(String id);
}
