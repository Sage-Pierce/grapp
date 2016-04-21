package com.wisegas.user.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.user.domain.entity.User;
import com.wisegas.user.domain.repository.UserRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

@Named
@Singleton
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

   @Override
   public Optional<User> findByEmail(String email) {
      try {
         return Optional.of(entityManager.createQuery(" SELECT user " +
                                                      " FROM User user " +
                                                      " WHERE user.id.email = :email",
                                                      User.class)
                                         .setParameter("email", email)
                                         .getSingleResult());
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }
}
