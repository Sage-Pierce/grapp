package com.wisegas.grapp.itemmanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.grapp.itemmanagement.domain.entity.GrappItem;
import com.wisegas.grapp.itemmanagement.domain.repository.GrappItemRepository;
import com.wisegas.grapp.itemmanagement.domain.value.GrappItemCode;

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

   @Override
   public Optional<GrappItem> findByCode(GrappItemCode code) {
      try {
         return Optional.of((GrappItem)entityManager.createQuery(" SELECT grappItem" +
                                                                 " FROM GrappItem grappItem" +
                                                                 "    JOIN grappItem.codes code" +
                                                                 " WHERE code.type = :codeType" +
                                                                 "    AND code.value = :codeValue")
                                                    .setParameter("codeType", code.getType())
                                                    .setParameter("codeValue", code.getValue())
                                                    .getSingleResult());
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }
}
