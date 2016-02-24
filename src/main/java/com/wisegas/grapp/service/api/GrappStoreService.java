package com.wisegas.grapp.service.api;

import com.wisegas.grapp.service.dto.GrappStoreDTO;
import com.wisegas.lang.GeoPoint;

import java.util.List;

public interface GrappStoreService {
   GrappStoreDTO createForOwner(String ownerID, String name, GeoPoint location);

   List<GrappStoreDTO> findAllForOwner(String ownerID);

   GrappStoreDTO findByID(String id);

   GrappStoreDTO updateName(String id, String name);

   GrappStoreDTO updateLocation(String id, GeoPoint location);

   void deleteByID(String id);
}
