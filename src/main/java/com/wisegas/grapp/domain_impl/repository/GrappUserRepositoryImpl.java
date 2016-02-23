package com.wisegas.grapp.domain_impl.repository;

import com.wisegas.grapp.domain.entity.GrappUser;
import com.wisegas.grapp.domain.repository.GrappUserRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

@Named
@Singleton
public class GrappUserRepositoryImpl extends GenericEntityRepositoryImpl<GrappUser> implements GrappUserRepository {

   @Override
   public GrappUser findByEmail(String email) {
      List results = entityManager.createQuery(" SELECT grappUser " +
                                               " FROM GrappUser grappUser " +
                                               " WHERE grappUser.email = :email ")
                                  .setParameter("email", email)
                                  .getResultList();
      return results.size() == 1 ? (GrappUser)results.get(0) : null;
   }
}
