package com.wisegas.itemmanagement.domain_impl.repository;

import com.wisegas.common.persistence.jpa.impl.GenericRepositoryImpl;
import com.wisegas.itemmanagement.domain.entity.Item;
import com.wisegas.itemmanagement.domain.repository.ItemRepository;
import com.wisegas.itemmanagement.domain.value.Code;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Named
@Singleton
public class ItemRepositoryImpl extends GenericRepositoryImpl<Item> implements ItemRepository {

   @Override
   public List<Item> getGeneralItems() {
      return entityManager.createQuery(" SELECT item" +
                                       " FROM Item item" +
                                       " WHERE item.superItem IS NULL")
                          .getResultList();
   }

   @Override
   public Optional<Item> findByName(String name) {
      try {
         return Optional.of((Item)entityManager.createQuery(" SELECT item" +
                                                            " FROM Item item" +
                                                            " WHERE item.name = :name")
                                               .setParameter("name", name)
                                               .getSingleResult());
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }

   @Override
   public Optional<Item> findByCode(Code code) {
      try {
         return Optional.of((Item)entityManager.createQuery(" SELECT item" +
                                                            " FROM Item item" +
                                                            " WHERE item.id = :code")
                                               .setParameter("code", code)
                                               .getSingleResult());
      }
      catch (Exception e) {
         return Optional.empty();
      }
   }
}
