package org.codegas.commons.persistence.api;

import org.codegas.commons.domain.entity.DomainEntity;
import org.codegas.commons.lang.value.Id;

import java.util.List;

public interface GenericRepository<T extends DomainEntity> {

    T add(T t);

    T remove(Id id);

    T remove(T t);

    T get(Id id);

    List<T> get();
}
