package org.codegas.commons.persistence.jpa.impl;

import org.codegas.commons.domain.entity.DomainEntity;
import org.codegas.commons.lang.value.Id;
import org.codegas.commons.persistence.api.GenericRepository;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericRepositoryImpl<T extends DomainEntity> implements GenericRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected final Class<T> entityClass;

    public GenericRepositoryImpl() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T add(T t) {
        entityManager.persist(t);
        return t;
    }

    @Override
    public T remove(Id id) {
        return remove(get(id));
    }

    @Override
    public T remove(T t) {
        entityManager.remove(t);
        return t;
    }

    @Override
    public T get(Id id) {
        return entityManager.find(entityClass, convertIdToQueryObject(id));
    }

    @Override
    public List<T> get() {
        return entityManager.createQuery(" SELECT entity" +
                " FROM " + entityClass.getSimpleName() + " entity",
            entityClass)
            .getResultList();
    }

    protected Object convertIdToQueryObject(Id id) {
        Class idClass = id.getClass();
        return idClass.isPrimitive() || idClass.isAnnotationPresent(Embeddable.class) ? id : id.toString();
    }
}
