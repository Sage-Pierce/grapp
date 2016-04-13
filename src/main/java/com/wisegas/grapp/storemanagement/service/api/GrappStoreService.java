package com.wisegas.grapp.storemanagement.service.api;

import com.wisegas.common.lang.value.GeoPoint;
import com.wisegas.grapp.storemanagement.service.dto.GrappStoreDTOO;

import java.util.List;

public interface GrappStoreService {
   GrappStoreDTOO create(String name, GeoPoint location);

   List<GrappStoreDTOO> getAll();

   GrappStoreDTOO get(String id);

   GrappStoreDTOO update(String id, String name, GeoPoint location);

   void delete(String id);
}
