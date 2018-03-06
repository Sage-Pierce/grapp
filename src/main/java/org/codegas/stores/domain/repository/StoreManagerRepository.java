package org.codegas.stores.domain.repository;

import java.util.Optional;

import org.codegas.common.lang.value.Email;
import org.codegas.common.persistence.api.GenericRepository;
import org.codegas.stores.domain.entity.StoreManager;

public interface StoreManagerRepository extends GenericRepository<StoreManager> {

    Optional<StoreManager> findByEmail(Email email);
}
