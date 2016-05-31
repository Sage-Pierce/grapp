package com.wisegas.stores.domain_impl.repository;

import com.wisegas.common.lang.value.Email;
import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.stores.domain.entity.StoreManager;
import com.wisegas.stores.domain.repository.StoreManagerRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

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
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }
}
