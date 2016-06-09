package com.wisegas.common.domain.entity;

import com.wisegas.common.lang.value.Id;

import java.util.List;

public interface GenericRepository<T extends SimpleEntity> {
   T add(T t);

   T remove(Id id);

   T remove(T t);

   T get(Id id);

   List<T> getAll();
}
