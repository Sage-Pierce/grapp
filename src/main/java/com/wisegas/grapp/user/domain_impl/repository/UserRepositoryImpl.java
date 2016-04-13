package com.wisegas.grapp.user.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.user.domain.entity.User;
import com.wisegas.grapp.user.domain.repository.UserRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

@Named
@Singleton
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

   @Override
   public Optional<User> findByEmail(String email) {
      try {
         return Optional.of((User)entityManager.createQuery(" SELECT user " +
                                                            " FROM User user " +
                                                            " WHERE user.id.email = :email ")
                                               .setParameter("email", email)
                                               .getSingleResult());
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }
}
