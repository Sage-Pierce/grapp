package org.codegas.persistence.repository;

import org.codegas.commons.domain.entity.DomainEntity;
import org.codegas.commons.lang.value.Id;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T extends DomainEntity> {

    T add(T t);

    T remove(Id id);

    T remove(T t);

    T get(Id id);

    Optional<T> find(Id id);

    List<T> get();
}
