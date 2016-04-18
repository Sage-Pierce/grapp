package com.wisegas.storemanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.storemanagement.domain.entity.StoreManager;
import com.wisegas.storemanagement.domain.repository.StoreManagerRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

@Named
@Singleton
public class StoreManagerRepositoryImpl extends GenericRepositoryImpl<StoreManager> implements StoreManagerRepository {

   @Override
   public Optional<StoreManager> findByEmail(String email) {
      try {
         return Optional.of((StoreManager)entityManager.createQuery(" SELECT storeManager " +
                                                                    " FROM StoreManager storeManager " +
                                                                    " WHERE storeManager.id.email = :email ")
                                                       .setParameter("email", email)
                                                       .getSingleResult());
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }
}
