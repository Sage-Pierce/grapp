package com.wisegas.grapp.domain_impl.service_impl;

import com.wisegas.grapp.domain.service.GenericEntityRepository;
import com.wisegas.persistence.jpa.entity.SimpleEntity;
import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class GenericEntityRepositoryImpl<T extends SimpleEntity> implements GenericEntityRepository<T> {
   @PersistenceContext
   protected EntityManager entityManager;

   private final Class<T> entityClass;

   public GenericEntityRepositoryImpl() {
      entityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
   }

   @Override
   public T add(T t) {
      entityManager.persist(t);
      return t;
   }

   @Override
   public T findByID(EntityID id) {
      return (T)entityManager.createQuery(" SELECT entity" +
                                          " FROM " + getEntityClassName() + " entity" +
                                          " WHERE entity.id = :id")
                             .setParameter("id", id)
                             .getSingleResult();
   }

   @Override
   public T remove(T t) {
      entityManager.remove(t);
      return t;
   }

   protected String getEntityClassName() {
      return entityClass.getSimpleName();
   }
}
