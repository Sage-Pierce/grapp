package com.wisegas.common.persistence.jpa.api;

import com.wisegas.common.persistence.jpa.entity.SimpleEntity;
import com.wisegas.common.persistence.jpa.value.EntityId;

import java.util.List;

public interface GenericRepository<T extends SimpleEntity> {
   T add(T t);

   List<T> getAll();

   T get(EntityId entityId);

   void remove(EntityId entityId);
}
