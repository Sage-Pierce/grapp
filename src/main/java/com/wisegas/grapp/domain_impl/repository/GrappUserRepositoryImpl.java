package com.wisegas.grapp.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.domain.entity.GrappUser;
import com.wisegas.grapp.domain.repository.GrappUserRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

@Named
@Singleton
public class GrappUserRepositoryImpl extends GenericRepositoryImpl<GrappUser> implements GrappUserRepository {

   @Override
   public Optional<GrappUser> findByEmail(String email) {
      try {
         return Optional.of((GrappUser)entityManager.createQuery(" SELECT grappUser " +
                                                                 " FROM GrappUser grappUser " +
                                                                 " WHERE grappUser.email = :email ")
                                                    .setParameter("email", email)
                                                    .getSingleResult());
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }
}
