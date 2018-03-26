package org.codegas.test;

import groovy.lang.Closure;
import org.codegas.commons.domain.entity.DomainEntity;
import org.codegas.commons.lang.value.Id;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.Callable;

public class TestEntityManager {

   private EntityManager entityManager;
   private TransactionTemplate transactionTemplate;

   public TestEntityManager(EntityManager entityManager, PlatformTransactionManager platformTransactionManager) {
      this.entityManager = entityManager;
      this.transactionTemplate = new TransactionTemplate(platformTransactionManager);
   }

   public void save(final Iterable<?> entities) {
      runInTransaction(new Callable<Void>() {
         @Override
         public Void call() throws Exception {
            for (Object entity : entities) {
               entityManager.persist(entity);
            }
            return null;
         }
      });
   }

   public <T> T save(final T entity) {
      return runInTransaction(new Callable<T>() {
         @Override
         public T call() throws Exception {
            entityManager.persist(entity);
            return entity;
         }
      });
   }

   public void flush() {
      entityManager.flush();
   }

   public void clear() { entityManager.clear(); }

   public boolean contains(Object entity) {
      return entityManager.contains(entity);
   }

   public List query(String qlString) {
      return entityManager.createQuery(qlString).getResultList();
   }

   public <T extends DomainEntity> T getManagedEntity(T detachedEntity) {
      List<T> results = entityManager.createQuery(" SELECT entity" +
                                                  " FROM " + detachedEntity.getClass().getSimpleName() + " entity" +
                                                  " WHERE entity.id = :id")
                                     .setParameter("id", convertIdToQueryObject(detachedEntity.getId()))
                                     .getResultList();
      if (results.size() > 1) {
         throw new RuntimeException("There was more than one result when trying to fetch a Managed Entity for this Detached Entity: " + detachedEntity);
      }
      else {
         return results.isEmpty() ? null : results.get(0);
      }
   }

   public <T> T runInTransaction(final Closure<T> closure) {
      return runInTransaction(new Callable<T>() {
         @Override
         public T call() throws Exception {
            return closure.call();
         }
      });
   }

   private Object convertIdToQueryObject(Id id) {
      Class idClass = id.getClass();
      return idClass.isPrimitive() || idClass.isAnnotationPresent(Embeddable.class) ? id : id.toString();
   }

   private <T> T runInTransaction(final Callable<T> callable) {
      return transactionTemplate.execute(new TransactionCallback<T>() {
         @Override
         public T doInTransaction(TransactionStatus status) {
            try {
               return callable.call();
            }
            catch (Exception e) {
               System.err.println("Error running transaction in Integration Test.");
               e.printStackTrace();
            }
            return null;
         }
      });
   }
}
