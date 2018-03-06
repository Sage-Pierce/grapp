package org.codegas.common.persistence.api;

import java.util.List;

import org.codegas.common.domain.entity.SimpleEntity;
import org.codegas.common.lang.value.Id;

public interface GenericRepository<T extends SimpleEntity> {

    T add(T t);

    T remove(Id id);

    T remove(T t);

    T get(Id id);

    List<T> get();
}
