package com.wisegas.grapp.domain.repository;

import com.wisegas.grapp.domain.entity.GrappStore;
import com.wisegas.grapp.domain.value.GrappUserID;

import java.util.List;

public interface GrappStoreRepository extends GenericEntityRepository<GrappStore> {
   List<GrappStore> findAllForOwner(GrappUserID grappUserID);
}
