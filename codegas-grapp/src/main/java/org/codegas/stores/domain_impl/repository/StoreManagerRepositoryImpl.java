package org.codegas.stores.domain_impl.repository;

import java.util.Optional;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.value.Email;
import org.codegas.commons.persistence.jpa.repository.GenericRepositoryImpl;
import org.codegas.stores.domain.entity.StoreManager;
import org.codegas.stores.domain.repository.StoreManagerRepository;

@Named
@Singleton
public class StoreManagerRepositoryImpl extends GenericRepositoryImpl<StoreManager> implements StoreManagerRepository {

    @Override
    public Optional<StoreManager> findByEmail(Email email) {
        try {
            return Optional.of(entityManager.createQuery(" SELECT storeManager " +
                    " FROM StoreManager storeManager " +
                    " WHERE storeManager.email = :email",
                StoreManager.class)
                .setParameter("email", email.toString())
                .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
