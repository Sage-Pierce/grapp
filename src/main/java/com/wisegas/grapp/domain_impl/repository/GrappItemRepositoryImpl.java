package com.wisegas.grapp.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.domain.entity.GrappItem;
import com.wisegas.grapp.domain.repository.GrappItemRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Named
@Singleton
public class GrappItemRepositoryImpl extends GenericRepositoryImpl<GrappItem> implements GrappItemRepository {

   @Override
   public List<GrappItem> getGeneralItems() {
      return entityManager.createQuery(" SELECT grappItem" +
                                       " FROM GrappItem grappItem" +
                                       " WHERE grappItem.superItem IS NULL")
                          .getResultList();
   }

   @Override
   public Optional<GrappItem> findByName(String name) {
      try {
         return Optional.of((GrappItem)entityManager.createQuery(" SELECT grappItem" +
                                                                 " FROM GrappItem grappItem" +
                                                                 " WHERE grappItem.name = :name")
                                                    .setParameter("name", name)
                                                    .getSingleResult());
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }
}
