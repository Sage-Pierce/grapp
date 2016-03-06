package com.wisegas.persistence.jpa.impl;

import com.wisegas.persistence.jpa.entity.SimpleEntity;
import com.wisegas.persistence.jpa.api.GenericRepository;
import com.wisegas.persistence.jpa.value.EntityID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericRepositoryImpl<T extends SimpleEntity> implements GenericRepository<T> {
   @PersistenceContext
   protected EntityManager entityManager;

   private final Class<T> entityClass;

   public GenericRepositoryImpl() {
      entityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
   }

   @Override
   public T add(T t) {
      entityManager.persist(t);
      return t;
   }

   @Override
   public List<T> getAll() {
      return (List<T>)entityManager.createQuery(" SELECT entity" +
                                                " FROM " + getEntityClassName() + " entity")
                                   .getResultList();
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
