package com.wisegas.storemanagement.domain.repository;

import com.wisegas.common.persistence.jpa.api.GenericRepository;
import com.wisegas.storemanagement.domain.entity.StoreManager;

import java.util.Optional;

public interface StoreManagerRepository extends GenericRepository<StoreManager> {
   Optional<StoreManager> findByEmail(String email);
}
