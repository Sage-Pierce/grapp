package com.wisegas.common.lang.entity;

import java.util.List;

public interface GenericRepository<T extends SimpleEntity> {
   T add(T t);

   T remove(EntityId entityId);

   T get(EntityId entityId);

   List<T> getAll();
}
