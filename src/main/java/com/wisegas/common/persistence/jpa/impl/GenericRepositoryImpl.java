package com.wisegas.common.persistence.jpa.impl;

import com.wisegas.common.lang.entity.EntityId;
import com.wisegas.common.lang.entity.GenericRepository;
import com.wisegas.common.lang.entity.SimpleEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericRepositoryImpl<T extends SimpleEntity> implements GenericRepository<T> {
   @PersistenceContext
   protected EntityManager entityManager;

   protected final Class<T> entityClass;

   public GenericRepositoryImpl() {
      entityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
   }

   @Override
   public T add(T t) {
      entityManager.persist(t);
      return t;
   }

   @Override
   public T remove(EntityId id) {
      T entity = get(id);
      entityManager.remove(entity);
      return entity;
   }

   @Override
   public T get(EntityId id) {
      return entityManager.find(entityClass, id);
   }

   @Override
   public List<T> getAll() {
      return entityManager.createQuery(" SELECT entity" +
                                       " FROM " + entityClass.getSimpleName() + " entity",
                                       entityClass)
                          .getResultList();
   }
}
