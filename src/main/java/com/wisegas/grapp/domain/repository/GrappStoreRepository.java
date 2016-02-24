package com.wisegas.grapp.domain.repository;

import com.wisegas.grapp.domain.entity.GrappStore;
import com.wisegas.grapp.domain.value.GrappUserID;
import com.wisegas.persistence.jpa.repository_api.GenericRepository;

import java.util.List;

public interface GrappStoreRepository extends GenericRepository<GrappStore> {
   List<GrappStore> findAllForOwner(GrappUserID grappUserID);
}
