package com.wisegas.shoppinglist.domain_impl.repository;

import com.wisegas.common.lang.value.Email;
import com.wisegas.common.lang.value.Id;
import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.shoppinglist.domain.entity.Shopper;
import com.wisegas.shoppinglist.domain.repository.ShopperRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

@Named
@Singleton
public class ShopperRepositoryImpl extends GenericRepositoryImpl<Shopper> implements ShopperRepository {

   @Override
   public Optional<Shopper> findByEmail(Email email) {
      try {
         return Optional.of(entityManager.createQuery(" SELECT shopper " +
                                                      " FROM Shopper shopper " +
                                                      " WHERE shopper.id = :email",
                                                      Shopper.class)
                                         .setParameter("email", email.toString())
                                         .getSingleResult());
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }

   @Override
   protected Object convertIdToQueryObject(Id id) {
      return id.toString();
   }
}
