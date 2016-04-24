package com.wisegas.storemanagement.domain.repository;

import com.wisegas.common.lang.entity.GenericRepository;
import com.wisegas.storemanagement.domain.entity.StoreManager;

import java.util.Optional;

public interface StoreManagerRepository extends GenericRepository<StoreManager> {
   Optional<StoreManager> findByEmail(String email);
}
