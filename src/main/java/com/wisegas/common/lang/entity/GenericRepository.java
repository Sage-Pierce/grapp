package com.wisegas.common.lang.entity;

import java.util.List;

public interface GenericRepository<T extends SimpleEntity> {
   T add(T t);

   T remove(Id id);

   T get(Id id);

   List<T> getAll();
}
