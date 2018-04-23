package org.codegas.stores.domain.repository;

import java.util.Optional;

import org.codegas.commons.lang.value.Email;
import org.codegas.service.api.Repository;
import org.codegas.stores.domain.entity.StoreManager;

public interface StoreManagerRepository extends Repository<StoreManager> {

    Optional<StoreManager> findByEmail(Email email);
}
