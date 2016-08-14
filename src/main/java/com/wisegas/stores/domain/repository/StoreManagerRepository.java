package com.wisegas.stores.domain.repository;

import com.wisegas.common.persistence.api.GenericRepository;
import com.wisegas.common.lang.value.Email;
import com.wisegas.stores.domain.entity.StoreManager;

import java.util.Optional;

public interface StoreManagerRepository extends GenericRepository<StoreManager> {
   Optional<StoreManager> findByEmail(Email email);
}
